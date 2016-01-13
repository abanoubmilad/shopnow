<?php
/**
 * web service
 * get store related promotion
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    21/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "PROMOTIONS", "p" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['store_id'] ) ) {
        $store_id=$_POST['store_id'];
        if ( preg_match( "/[0-9]+/", $store_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $promotions=$opr -> get_store_related_promotions( $store_id );
            if ( $promotions===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[PROMOTIONS] = $promotions;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
