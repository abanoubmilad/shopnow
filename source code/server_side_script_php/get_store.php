<?php
/**
 * web service
 * get store
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "STORE", "r" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['store_id'] ) ) {
        $store_id=$_POST['store_id'];
        if ( preg_match( "/[0-9]+/", $store_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $store=$opr -> get_store( $store_id );
            if ( $store===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[STORE] = $store;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
