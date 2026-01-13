<?php
$host = "localhost";
$user = "root";
$pass = "";
$db   = "nmp_uas";

$conn = mysqli_connect($host, $user, $pass, $db);

if (!$conn) {
    die("Koneksi gagal");
}
?>
