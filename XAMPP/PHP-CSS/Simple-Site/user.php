<div class="containerPage", >
<?php

include ("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST["username"];
    $password = $_POST["parola"];
    $phone = $_POST["nrTelefon"];
    $orderBy = isset($_POST["order_by"]) ? $_POST["order_by"] : "";

    $sql1 = "SELECT * FROM utilizatori ORDER BY username";
    $result1 = $conn->query($sql1);
    
    $sql2 = "SELECT * FROM utilizatori ORDER BY id";
    $result2 = $conn->query($sql2);

    $query = "SELECT * FROM utilizatori WHERE username='$username' AND parola='$password' And nrTelefon='$phone'";
    $autentificare = mysqli_query($conn, $query);
    $count = mysqli_num_rows($autentificare);

    if ($count != 0) { 
        // Utilizatorul este autentificat cu succes
        echo "Logat cu succes:.......  ";
        $userInfo = mysqli_fetch_assoc($autentificare);

        // Stocăm informațiile utilizatorului în variabila de sesiune
        $_SESSION['authenticated_user'] = $username;
        $_SESSION['user_info'] = $userInfo;

        include("secure_page.php");

        if ($orderBy == "name") {
            // Restul codului pentru afișarea datelor ordonate după nume
        } 

        if ($orderBy == "id") {
            // Restul codului pentru afișarea datelor ordonate după ID
        } 
    } else {
        echo "Eroare de logare";
    }
}

mysqli_close($conn);
?>

<form method="post" action="login.php">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="parola">Password:</label>
    <input type="password" id="parola" name="parola" required><br>

    <label for="nrTelefon">Phone:</label>
    <input type="text" id="nrTelefon" name="nrTelefon" required><br>

    <p>Order by:</p>
    <label>
        <input type="radio" name="order_by" value="name"> Name
        <input type="radio" name="order_by" value="id"> ID
    </label><br>
    <br>

    <button type="submit">Login</button>
</form>

        <?php
           echo "<button type='Back'>
           <a href='index.php'>Back</a>
                </button>";
         ?>


</div>