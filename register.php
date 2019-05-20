<?php
require "conn.php";
$firstname = $_POST["firstname"];
$lastname = $_POST["lastname"];
$email = $_POST["email"];
$user_name = $_POST["user_name"];
$password = $_POST["password"];
$mysql_qry = "insert into login (firstname,lastname, email, user_name, password) values ('$firstname', '$lastname', '$email', '$user_name', '$password')";

if($conn->query($mysql_qry) === TRUE){
echo "Registration succesfull";
}
else{
echo"Error" .$mysql_qry . "<br>" .$conn->error;
}
$conn->close();
?>
