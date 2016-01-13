<?php
/**
 * get subcategories web service
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "SUBCATEGORY", "c" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['cat'] ) ) {
        $cat=$_POST['cat'];
        if ( preg_match( "/[a-zA-Z]{5,20}/", $cat ) ) {
            include "Opr.php";
            $opr=new Opr();
            $sub=$opr -> get_item_sub( $cat );
            if ( $sub===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[SUBCATEGORY]=$sub;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
