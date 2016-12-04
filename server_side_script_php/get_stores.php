<?php
/**
 * web service
 * get stores
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "STORES", "r" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
	include "Opr.php";
	$opr=new Opr();
	$stores=$opr -> get_stores();
	if ( $stores===false )
		$response[STATUS] = 3;
	else {
		$response[STATUS] = 7;
		$response[STORES] = $stores;
	}
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
