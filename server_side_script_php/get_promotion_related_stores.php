<?php
/**
 * web service
 * get promotion related stores
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
    if ( isset( $_POST['promotion_id'] ) ){
        $promotion_id=$_POST['promotion_id'];
        if ( preg_match( "/[0-9]+/", $promotion_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $stores=$opr -> get_promotion_related_stores( $promotion_id );
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
