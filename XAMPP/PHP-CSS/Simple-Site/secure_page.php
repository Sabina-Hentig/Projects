<?php

if (!isset($_SESSION['authenticated_user'])) {
    header("Location: index.php");
    
    exit();
}

echo "<p>Welcome, " . $_SESSION['authenticated_user'] . "!</p>";

?>
<?php
        echo "<button class='logout'>
                    <a href='logout.php'>Logout</a>
                </button>";
        ?>
