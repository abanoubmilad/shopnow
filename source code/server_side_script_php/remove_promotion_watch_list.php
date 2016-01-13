<?php
/**
 * web service
 * remove promotion watch list
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['item_id'] ) && isset( $_POST['info_id'] ) ) {
        $item_id=$_POST['item_id'];
        $info_id=$_POST['info_id'];
        $name=$_POST['name'];
        if ( preg_match( "[0-9]+", $item_id ) && preg_match( "/[0-9]+/", $info_id ) ) {
            include "Opr.php";
            $opr=new Opr();
            $check=$opr -> remove_item_watch_list( $_SESSION['id'], $item_id, $info_id );
            if ( $check===false )
                $response[STATUS] = 3;
            else
                $response[STATUS] = 7;
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;
echo json_encode( $response );
?>
