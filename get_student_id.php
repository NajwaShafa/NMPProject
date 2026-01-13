<?php
include "koneksi.php";

$nrp = $_GET['nrp'];

$sql = "SELECT * FROM student WHERE nrp = '$nrp'";
$result = mysqli_query($conn, $sql);

$data = array();

if ($row = mysqli_fetch_assoc($result)) {
    $data = $row;
}

echo json_encode($data);
?>
