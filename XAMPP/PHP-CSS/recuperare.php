<?php include("head.php"); ?>

<h2>Recupeare Parola</h2>
<?php
session_start();

include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = isset($_POST['username']) ? htmlspecialchars($_POST['username']) : '';
    $email = isset($_POST['email']) ? htmlspecialchars($_POST['email']) : '';

    if (empty($username)  || empty($email)) {
        echo "ERROR: All fields are required.";
    } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "ERROR: Invalid email address.";
    } else {
        $query = "SELECT * FROM utilizatori WHERE username='$username' AND email='$email'";
        $autentificare = mysqli_query($conn, $query);
        $count = mysqli_num_rows($autentificare);
    
        if ($count != 0) { 
            echo "Logat cu succes:.......  ";
            $userInfo = mysqli_fetch_assoc($autentificare);
    
            $_SESSION['authenticated_user'] = $username;
            $_SESSION['user_info'] = $userInfo;
    
            function randomPassword() {
                $alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
                $pass = array();
                $alphaLength = strlen($alphabet) - 1; 
                for ($i = 0; $i < 8; $i++) {
                    $n = rand(0, $alphaLength);
                    $pass[] = $alphabet[$n];
                }
                return implode($pass); 
            }

           $newPass = randomPassword();
           
           $hashedPassword = password_hash($newPass, PASSWORD_DEFAULT);

           $sql = "UPDATE utilizatori SET password_hash = '$hashedPassword', parola= '$newPass' WHERE  username='$username' AND email='$email' ";

            if ($conn->query($sql) === TRUE) {
                echo "Parola a fost salvată cu succes!";
                $adresaEmail = $email;

                $subiect = "Schimbare parolă - pt examen";

                $mesaj = "Parola dumneavoastră pe pagina xy a fost schimbată cu succes. Puteți accesa pagina folosind parola generată.";


                $antete = "From: examen@ubb.com\r\n";
                $antete .= "Reply-To: hsabinamarai@gmail.com\r\n";
                $antete .= "Content-Type: text/plain; charset=UTF-8\r\n";

                mail($adresaEmail, $subiect, $mesaj, $antete);

                echo "E-mail trimis cu succes!";
            } else {
                echo "Eroare la salvarea parolei: " . $conn->error;
            }

            include("secure_page.php");

        } else {
            echo "Eroare de logare";
        }

    }
exit();
}

mysqli_close($conn);


?>

<form method="post">
    <label  for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" required><br>
    <br>

    <button type="submit">Modificare parola</button>
</form>

        <?php
           echo "<button type='Back'>
           <a href='index.php'>Back</a>
                </button>";
         ?>