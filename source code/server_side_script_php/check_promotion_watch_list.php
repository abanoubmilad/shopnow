<?php
/**
 * web service
 * check promotion watch list
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    28/08/2015
 * @edited     28/08/2015
 */
define( "STATUS", "s" );
define( "CHECK", "c" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    include "Opr.php";
    $opr=new Opr();
    $check_list=$opr -> check_promotion_watch_list( $_SESSION['id'] );
    if ( $check_list===false )
        $response[STATUS] = 3;
    else {
        $response[STATUS] = 7;
        $response[CHECK] = $check_list;
    }
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
