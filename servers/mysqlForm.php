<?php

$host = "localhost";
$user = "root";
$pass = "";
$db = "mydatabase";
$conn = new mysqli($host, $user, $pass, $db);
if ($conn -> connect_error) {
    echo "Failed to connect DB".$conn -> connect_error;
}

?>



<?php

if (isset($_POST['signUp'])) {
    $firstName = $_POST['fname'];
    $lastName = $_POST['lname'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $password = md5($password);

    $checkEmail = "SELECT * FROM users WHERE email = '$email'";
    $result = $conn -> query($checkEmail);
    if ($result -> num_rows > 0) {
        echo "Email Already Exists !";
    } else {
        $insertQuery = "INSERT INTO users(firstName, lastName, email, password) VALUES ('$firstName', '$lastName', '$email', '$password')";
        if ($conn -> query($insertQuery) == TRUE) {
            header("location: index.html");
        } else {
            echo "Error:".$conn -> error;
        }
    }
}

if (isset($_POST['signIn'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];
    $password = md5($password);

    $sql = "SELECT * FROM users WHERE email = '$email' and password = '$password'";
    $result = $conn -> query($sql);

    if ($result -> num_rows > 0) {
        session_start();
        $row = $result -> fetch_assoc();
        $_SESSION['email'] = $row['email'];
        header("location: index.html");
        exit();
    } else {
        echo "Not Found, Incorrect Email or Password";
    }

}

?>



<?php

session_start();


?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
</head>
<body>
    <div style="text-allign:center; padding: 15px;">
        <p style="font-size: 50px; font-weight: bold;">
        
        Hello
            <?php
            
            if (isset($_SESSION['email'])) {
                $email = $_SESSION['email'];
                $query = mysqli_query($conn, "SELECT users. * FROM 'users' WHERE users.email = '$email'");
                while($row = mysqli_fetch_array($query)) {
                    echo $row['firstName'].''.$row['lastName'];
                }
            }
            
            ?>
            :)
        </p>
    </div>
</body>
</html>



<?php

session_destroy():
header("location: index.html");

?>