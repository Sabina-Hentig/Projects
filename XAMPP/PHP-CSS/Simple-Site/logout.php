<?php
session_start();

// Distruge sesiunea și redirectionează către pagina de login
session_destroy();
header("Location: login.php");
exit();
?>
