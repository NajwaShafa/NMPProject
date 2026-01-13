<?php
include "koneksi.php";

$nrp = $_POST['nrp'];

// cek sudah friend atau belum
$cek = mysqli_query($conn, "SELECT * FROM my_friends WHERE nrp = '$nrp'");

if (mysqli_num_rows($cek) == 0) {
    mysqli_query($conn, "INSERT INTO my_friends (nrp) VALUES ('$nrp')");
}

$count = mysqli_query($conn, "SELECT COUNT(*) as total FROM my_friends");
$row = mysqli_fetch_assoc($count);

echo json_encode($row);
?>
