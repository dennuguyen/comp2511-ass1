javac unsw/skydiving/*.java -cp ../lib/*
java -cp ../lib/*: unsw.skydiving.SkydiveBookingSystem "$@"
rm unsw/skydiving/*.class
