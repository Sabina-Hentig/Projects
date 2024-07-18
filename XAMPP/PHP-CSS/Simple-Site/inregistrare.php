<div class="containerPage">
    <?php include("head.php"); ?>
    <link rel="stylesheet" type="text/css" href="style.css">

    <body>
        <?php

       include("config.php");    


        if ($_SERVER["REQUEST_METHOD"] == "POST" || (isset($_POST['save_radio']))) {
            $username = isset($_POST['username']) ? htmlspecialchars($_POST['username']) : '';
            $parola = isset($_POST['parola']) ? htmlspecialchars($_POST['parola']) : '';
            $starecivila = isset($_POST['starecivila']) ? htmlspecialchars($_POST['starecivila']) : '';
            $sex = isset($_POST['sex']) ? htmlspecialchars($_POST['sex']) : '';
            $nume = isset($_POST['nume']) ? htmlspecialchars($_POST['nume']) : '';
            $prenume = isset($_POST['prenume']) ? htmlspecialchars($_POST['prenume']) : '';
            $email = isset($_POST['email']) ? htmlspecialchars($_POST['email']) : '';
            $phone = isset($_POST['nrTelefon']) ? htmlspecialchars($_POST['nrTelefon']) : '';
            $poza = isset($_FILES['poza']['name']) ? htmlspecialchars($_FILES['poza']['name']) : '';
            $data_registrari = isset($_POST['data_registrari']) ? htmlspecialchars($_POST['data_registrari']) : '';

            if (empty($username) || empty($parola) || empty($starecivila) || empty($sex) || empty($nume) || empty($prenume) || empty($email)|| empty($phone) || empty($poza)|| empty($data_registrari)) {
                echo "ERROR: All fields are required.";
            } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                echo "ERROR: Invalid email address.";
            }elseif (!preg_match("/^[0-9]{10}$/", $phone)){
                echo "ERROR: Invalid phone number.";
            } else {
                $target_dir = "poze/";
                $target_file = $target_dir . basename($_FILES["poza"]["name"]);
                $uploadOk = 1;
                $imageFileType = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));
                // Verificare dacă fișierul este imagine
                $check = getimagesize($_FILES["poza"]["tmp_name"]);
                if($check !== false) {
                    echo "File is an image - " . $check["mime"] . ".";
                    $uploadOk = 1;
                } else {
                    echo "File is not an image.";
                    $uploadOk = 0;
                }

                if ($uploadOk == 0) {
                    echo "Sorry, your file was not uploaded.";
                } else {
                    if (move_uploaded_file($_FILES["poza"]["tmp_name"], $target_file)) {
                    } else {
                        echo "Sorry, there was an error uploading your file.";
                    }
                }

            
                $data_registrari = date('Y-m-d'); 
            
                if ($uploadOk != 0) {
                    $sql = "INSERT INTO utilizatori (username, parola, starecivila, sex, nume, prenume, email, nrTelefon, poza, data_registrari)  
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                    $stmt = mysqli_prepare($conn, $sql);
                
                    if ($stmt) {
                        mysqli_stmt_bind_param($stmt, "ssssssssss", $username, $parola, $starecivila, $sex, $nume, $prenume, $email, $phone, $poza, $data_registrari);
                
                        if (mysqli_stmt_execute($stmt)) {
                            echo "<h3>Data stored in the database successfully."
                                . " Please browse your localhost php my admin"
                                . " to view the updated data</h3>";

                            //  echo nl2br("\n$username\n $parola\n $starecivila\n $sex\n $nume\n $prenume\n $email\n $phone\n $poza\n $data_registrari\n");
                        } else {
                            echo "ERROR: Hush! Sorry. " . mysqli_stmt_error($stmt);
                        }

                        mysqli_stmt_close($stmt);
                    } else {
                        echo "ERROR: Hush! Sorry. " . mysqli_error($conn);
                    }
                }
            }
        }


        mysqli_close($conn);
    ?>
        <div class="container">
            <form method="post" enctype="multipart/form-data">
                    <p>
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username">
                    </p>
                    <p>
                        <label for="parola">Password:</label>
                        <input type="password" name="parola" id="parola">
                    </p>
                    <p>
                        <label for="starecivila">Stare civila:</label>
                        <div class="radio">
                        <br>
                            <input type="radio" name="starecivila" value="casatorit">
                            <label for="casatorit"> Casatorit</label><br>
                            <input type="radio" name="starecivila" value="necasatorit">
                            <label for="necasatorit"> Necasatorit</label><br>
                            <input type="radio" name="starecivila" value="divortat">
                            <label for="divortat"> Divortat</label><br>
                            <input type="radio" name="starecivila" value="vaduv">
                            <label for="vaduv"> Vaduv/a</label><br>
                        </div>
                    </p>
                    <p>
                        <label for="sex">Sex:</label>
                        <div class="radio">
                        <br>
                            <input type="radio" name="sex" value="Masculin">
                            <label for="MASCULIN"> Masculin</label><br>
                            <input type="radio" name="sex" value="Feminin">
                            <label for="FEMININ"> Feminin</label><br>
                            <input type="radio" name="sex" value="Non-Binary">
                            <label for="Non-Binary"> Non-Binary</label><br>
                        </div>
                    </p>
                    <p>
                        <label for="nume">Nume:</label>
                        <input type="text" name="nume" id="nume">
                    </p>
                    <p>
                        <label for="prenume">Prenume:</label>
                        <input type="text" name="prenume" id="prenume">
                    </p>
                    <p>
                        <label for="email">Email:</label>
                        <input type="text" name="email" id="email">
                    </p>

                    <p>
                        <label for="nrTelefon">Nr. Telefon:</label>
                        <input type="text" name="nrTelefon" id="nrTelefon">
                    </p>

                    <p>
                        <label for="poza">Poza:</label>
                        <input type="file" name="poza" id="poza">
                    </p>

                    <p>
                        <label for="date_registari">Data Registrare:</label>
                        <input type="date" name="data_registrari" id="data_registrari">
                    </p>
                    <input type="submit" value="Submit">
                </form>
        </div>
    </body>

        <?php
        echo "<button class='back'>
                    <a href='index.php'>Back</a>
                </button>";
        ?>
</div>


