<?php
/**
 * Database wrapper class
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    06/08/2015
 * @edited     20/08/2015
 */
class DB {
	// The database connection
	protected static $connection;
	/**
	 *
	 *
	 * @return bool false on failure / mysqli MySQLi object instance on success
	 */
	public function connect() {
		// Try and connect to the database
		if ( !isset( self::$connection ) ) {
			// Load configuration as an array. Use the actual location of configuration file
			// Put the configuration file outside of the document root
			$config = parse_ini_file( './config.ini' );
			self::$connection = new mysqli( $config['host'], $config['username'], $config['password'], $config['dbname'] );
		}

		// If connection was not successful, handle the error
		if ( self::$connection === false ) {
			// Handle error - notify administrator, log to a file, show an error screen, etc.
			return false;
		}
		return self::$connection;
	}

	/**
	 * Query the database
	 *
	 * @param unknown $query The query string
	 * @return mixed The result of the mysqli::query() function
	 */
	public function query( $query ) {
		// Connect to the database
		$connection = $this -> connect();

		// Query the database
		return  $connection -> query( $query );
	}

	/**
	 * Fetch the last error from the database
	 *
	 * @return string Database error message
	 */
	public function error() {
		$connection = $this -> connect();
		return $connection -> error;
	}

	/**
	 * Quote and escape value for use in a database query
	 *
	 * @param string  $value The value to be quoted and escaped
	 * @return string The quoted and escaped string
	 */
	public function quote( $value ) {
		$connection = $this -> connect();
		return "'" . $connection -> real_escape_string( $value ) . "'";
	}

	/**
	 * insert to the database
	 *
	 * @param unknown $query The query string
	 * @return integer id of the inserted row
	 */
	public function insert( $query ) {
		$connection = $this -> connect();
		$connection->query( $query );
		return mysqli_insert_id( $connection );
	}

	/**
	 * query update, delete or insert (if row id not required) and checks if rows were affected
	 *
	 * @param string  $query The query string
	 * @return bool true if rows were affected, false otherwise
	 */
	public function query_and_check( $query ) {
		$connection = $this -> connect();
		$connection->query( $query );
		return mysqli_affected_rows( $connection ) > 0;
	}
}
