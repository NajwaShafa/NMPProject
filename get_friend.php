<?php
include "koneksi.php";

$sql = "
SELECT student.*
FROM my_friends
INNER JOIN student ON my_friends.nrp = student.nrp
";

$result = mysqli_query($conn, $sql);

$data = array();

while ($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode($data);
?>
