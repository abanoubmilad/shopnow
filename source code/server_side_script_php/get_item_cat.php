<?php
/**
 * web service
 * get item categories
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "CATEGORY", "c" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
	include "Opr.php";
	$opr=new Opr();
	$cat=$opr -> get_item_categories();
	if ( $cat===false )
		$response[STATUS] = 3;
	else {
		$response[STATUS] = 7;
		$response[CATEGORY]=$cat;
	}
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
