<?php
/**
 * web service
 * sign in
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    09/08/2015
 * @edited     23/08/2015
 */
define( "STATUS", "s" );
$response = array();
if ( isset( $_POST['email'] ) && isset( $_POST['pass'] ) ) {
    $email = $_POST['email'];
    $password = $_POST['pass'];
    if ( filter_var( $email, FILTER_VALIDATE_EMAIL ) && preg_match( "/[a-zA-Z0-9]{5,20}/", $password ) ) {
        include "Opr.php";
        $opr=new Opr();
        $id=$opr -> get_id( $email, $password );
        if ( $id===false )
            $response[STATUS] = 3;
        else {
            session_start();
            $_SESSION['id'] = $id;
            $response[STATUS] = 7;
        }

    }else
        $response[STATUS] = 2;

} else
    $response[STATUS] = 1;

echo json_encode( $response );
?>
