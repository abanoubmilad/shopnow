<?php
/**
 * web service
 * get promotion
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    21/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "PROMOTION", "p" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['promotion_id'] ) ) {
        $promotion_id=$_POST['promotion_id'];
        if ( preg_match( "/[0-9]+/", $promotion_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $promotion=$opr -> get_promotion( $promotion_id );
            if ( $promotion===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[PROMOTION] = $promotion;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
