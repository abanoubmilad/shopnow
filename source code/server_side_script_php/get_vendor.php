<?php
/**
 * web service
 * get vendor
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    21/08/2015
 * @edited     21/08/2015
 */
define( "STATUS", "s" );
define( "VENDOR", "v" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['vendor_id'] ) ) {
        $vendor_id=$_POST['vendor_id'];
        if ( preg_match( "/[0-9]+/", $vendor_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $vendor=$opr -> get_vendor( $vendor_id );
            if ( $vendor===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[STORE] = $vendor;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;
echo json_encode( $response );
?>
