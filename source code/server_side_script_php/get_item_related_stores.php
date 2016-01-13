<?php
/**
 * web service
 * get item related stores
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "STORES", "t" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['item_id'] ) && isset( $_POST['info_id'] ) ) {
        $item_id=$_POST['item_id'];
        $info_id=$_POST['info_id'];
        if ( preg_match( "/[0-9]+/", $item_id ) && preg_match( "/[0-9]+/", $info_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $stores=$opr -> get_item_related_stores( $item_id, $info_id );
            if ( $stores===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[STORES] = $stores;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
