<?php
require "conn.php";
$user_name = $_POST["user_name"];
$password = $_POST["password"];
$mysql_qry = "select * from login where user_name like '$user_name' and password like '$password' ";
$result = mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($result) > 0){
echo "login success";
}
else{
echo"login failed";
}
?>