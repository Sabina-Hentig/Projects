
<?php include("head.php"); ?>

<h2>Index Page</h2>
<?php
session_start();

include("config.php");

if (isset($_SESSION['authenticated_user'])) {
    echo "<button class='logout'><a href='logout.php'>Logout</a></button>";

    $order_by = isset($_SESSION['authenticated_user']['order_by']) ? $_SESSION['authenticated_user']['order_by'] : "";

    $order_by_clause = ($order_by == 'name') ? 'ORDER BY username' : 'ORDER BY id';

    $sql = "SELECT * FROM utilizatori $order_by_clause";
    $result = $conn->query($sql);

    echo "<div class='container'>";

    if ($result) {
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                echo "<div class='user-card'>
                        <img src='poze/" . $row["poza"] . "' alt='poza utilizator'>
                        <p>ID: " . $row["id"] . "</p>
                        <p>Username: " . $row["username"] . "</p>
                        <p>Date Registration: " . $row["data_registrari"] . "</p>
                        <p>Phone number: " . $row["nrTelefon"] . "</p>
                      </div>";
            }
        } else {
            echo "<p>No results</p>";
        }
    } else {
        echo "<p>Error in query: " . $conn->error . "</p>";
    }

    echo "</div>";
} else {
        echo "<Button type='register'>
                <a href='inregistrare.php'>Register</a>
              </Button>";
        echo "<Button type='login'>
                <a href='login.php'>Login</a>
              </Button>";
        echo "<Button type='Recuperare'>
                <a href='recuperare.php'>Recuperare parola</a>
              </Button>";
}
?>
