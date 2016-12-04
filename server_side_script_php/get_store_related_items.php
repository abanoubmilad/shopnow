<?php
/**
 * web service
 * get store related items
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     21/08/2015
 */
define( "STATUS", "s" );
define( "ITEMS", "i" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['store_id'] ) ) {
        $store_id=$_POST['store_id'];
        if ( preg_match( "/[0-9]+/", $store_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $items=$opr -> get_store_related_items( $store_id );
            if ( $items===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[ITEMS] = $items;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
