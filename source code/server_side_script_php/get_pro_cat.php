<?php
/**
 * web service
 * get categories
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
	$categories=$opr -> get_promotion_categories();
	if ( $categories===false )
		$response[STATUS] = 3;
	else {
		$response[STATUS] = 7;
		$response[CATEGORY]=$categories;
	}
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
