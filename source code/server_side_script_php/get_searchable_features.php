<?php
/**
 * web service
 * get searchable items
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    28/08/2015
 * @edited     30/08/2015
 */
define( "STATUS", "s" );
define( "ITEMS", "f" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['info_id']) ) {
        $info_id=$_POST['info_id'];
        if ( preg_match( "/[0-9]+/", $info_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $items=$opr -> get_searchable_items( $info_id );
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
