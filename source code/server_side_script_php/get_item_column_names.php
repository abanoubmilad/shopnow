<?php
/**
 * web service
 * get item column names
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    21/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
define( "NAMES", "n" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['info_id'] ) ) {
        $info_id=$_POST['info_id'];
        if ( preg_match( "/[0-9]+/", $info_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $names=$opr -> get_item_column_names( $info_id );
            if ( $names===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[NAMES] = $names;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
