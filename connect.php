<?php
header('Content-type : bitmap; charset=utf-8');
if(isset($_POST["encoded_string"])){
    $encoded_string = $_POST["encoded_string"];
    $image_name = $_POST["image_name"];
    $decoded_string = base64_decode($encoded_string);
    $path='http://localhost/images/'.$image_name;
    $file = fopen($path, 'wb');
    
    $is_written = fwrite($file, $decoded_string);
    fclose($file);
    if($is_written > 0){
        $connection = mysqli_connect('localhost', 'root', '', 'tutorials');
        $query = "INSERT INTO photos(name, path) VALUES('$image_name','$path');";
        $result = mysqli_query($connection, $query);
        if($result){
            echo "success";
        }
        else{
            echo "failed";
        }
        mysqli_close($connection);
    }
}
?>