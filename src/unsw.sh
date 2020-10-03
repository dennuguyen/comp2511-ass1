set -x
javac unsw/skydiving/*.java -cp ../lib/json-20200518.jar
java unsw.skydiving.SkydiveBookingSystem
set +x