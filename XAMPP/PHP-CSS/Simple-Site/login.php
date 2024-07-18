<div class="containerPage">
    <?php include("head.php"); ?>
    <link rel="stylesheet" type="text/css" href="style.css">

<?php
session_start();

include("config.php");

    if (isset($_SESSION['authenticated_user'])) {
        header("Location: secure_page.php");
        exit();
    }else{
        include("user.php");
    }

    ?>


