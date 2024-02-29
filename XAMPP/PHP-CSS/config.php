<?php
// Configurările pentru conexiunea la baza de date, dacă este necesar
$servername = "localhost";
$username = "root";
$password = "";
$database = "userapp";

$conn = new mysqli($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Conexiunea la baza de date a eșuat: " . $conn->connect_error);
}
