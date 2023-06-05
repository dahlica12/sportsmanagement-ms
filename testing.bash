set -e

echo "HOST=${HOST}"
echo "PORT=${PORT}"

if [[ $@ == *"start"* ]]
then
  echo "Starting the test environment"
  docker-compose down
  echo "$ docker-compose up"
  docker-compose up -d
fi

function assertCurl() {
local expectedHttpCode=$1
local curlCmd="$2 -w \"%{http_code}\""
local result=$(eval $curlCmd)
local httpCode="${result:(-3)}"
RESPONSE='' && (( ${#result} > 3 )) && RESPONSE="${result%???}"
if [ "$httpCode" = "$expectedHttpCode" ]
then
if [ "$httpCode" = "200" ]
then
echo "Test OK (HTTP Code: $httpCode)"
else
echo "Test OK (HTTP Code: $httpCode, $RESPONSE)"
fi
else
echo "Test FAILED, EXPECTED HTTP Code: $expectedHttpCode, GOT: $httpCode, WILL ABORT!"
echo "- Failing command: $curlCmd"
echo "- Response Body: $RESPONSE"
exit 1
fi
}

function assertEqual() {
local expected=$1
local actual=$2
if [ "$actual" = "$expected" ]
then
echo "Test OK (actual value: $actual)"
else
echo "Test FAILED, EXPECTED VALUE: $expected, ACTUAL VALUE: $actual, WILL ABORT"
exit 1
fi
}


function setupTestData() {
body=\
'{
"athleteId":"c3ce7a49-f789-455e-8e7a-5fc20d491fa6",
"coachId":"573a1f61-9431-4c40-83e6-e1b090d7d10a",
"eventType":"GAME",
"status":"IN_PROGRESS",
"eventDate":"2023-06-20",
"streetAddress":"308 rue Blanche",
"city":"Montreal",
"province":"Quebec",
"country":"Canada",
"postalCode":"H7R 1E2",
"duration":"90",
"score":"TBA"
}'
recreateEventAggregate 1 "$body" "c3540a89-cb47-4c96-888e-ff96708db4d8"

#Variable to hold Sport? ids created by POST requests
allTestEventIds=()
function recreateEventAggregate() {
local testId=$1
local aggregate=$2
local sportId=$3
#create the event aggregates and record the generated eventIds
eventId=$(curl -X POST http://$HOST:$PORT/api/v1/sports/$sportId/events -H
"Content-Type:
application/json" --data "$aggregate" | jq '.eventId')
allTestEventIds[$testId]=$eventId
echo "Added Event Aggregate with eventId: ${allTestEventIds[$testId]}"
}

## Verify that a normal get by id of earlier posted event works
echo -e "\nTest 1: Verify that a normal get by id of earlier posted event works"
assertCurl 200 "curl http://$HOST:$PORT/api/v1/sports/0e68ddb1-aa16-41bc-8aad-c9e8c80f3efd/events/${allTestEventIds[1]} -s"
assertEqual ${allTestPurchaseOrderIds[1]} $(echo $RESPONSE | jq .purchaseOrderId)
assertEqual "\"Jersey Tigers\"" $(echo $RESPONSE | jq ".team1Name")

if [[ $@ == *"stop"* ]]
then
  echo "Stopping the test environment"
  echo "$ docker-compose down"
  docker-compose down
fi