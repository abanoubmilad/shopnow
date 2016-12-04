<?php
/**
 * web service
 * get item column features
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    28/08/2015
 * @edited     28/08/2015
 */
define( "STATUS", "s" );
define( "FEATURES", "f" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['info_id'] ) && isset( $_POST['column_index'] ) ) {
        $info_id=$_POST['info_id'];
        $column_index=$_POST['column_index'];
        if ( preg_match( "/[0-9]+/", $info_id ) && preg_match( "/[0-9]+/", $column_index ) ) {
            include "Opr.php";
            $opr=new Opr();
            $features=$opr -> get_item_column_features( $info_id , $column_index );
            if ( $features===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[FEATURES] = $features;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
