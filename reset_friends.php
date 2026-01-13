<?php
include "koneksi.php";

mysqli_query($conn, "DELETE FROM my_friends");

echo json_encode([
    "status" => "success"
]);
?>
