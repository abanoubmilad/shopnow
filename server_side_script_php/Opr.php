<?php
/**
 * Shopping Operations class
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    06/08/2015
 * @edited     16/09/2015
 */
class Opr {
	const INFO_TB = "db_info", COLUMN_NAMES_TB = "column_names",
	VENDOR_TB = "vendor", VENDOR_ITEM_TB = "vendor_item",
	STORE_TB = "store", CLIENT_TB = "client",
	PROMOTION_TB = "promotion", PROMOTION_WATCHLIST_TB = "promotion_watch_list",
	ITEM_WATCHLIST_TB = "item_watch_list", PROMOTION_STORE_TB = "promotion_store",
	ITEMS_KEYS="items_keys";

	// The database connection
	protected static $db;
	function __construct() {
		include 'DB.php';
		self::$db = new DB();
	}




	/*                    client related operations go here          */





	/**
	 * validate the client access to the database
	 *
	 * @param string  $email the client's email
	 * @param string  $pass  the client's password
	 * @return int client's id if exists, false otherwise
	 */
	public function get_id( $email, $password ) {
		$query ="SELECT id FROM ". self::CLIENT_TB ." WHERE email='$email' AND password='$password' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['id'];
		}
		return false;
	}
	/**
	 * check the existance of an email address in the database
	 *
	 * @param string  $email the client's email string
	 * @return bool true if email exists, false otherwise
	 */
	public function get_email( $email ) {
		$query ="SELECT id FROM ".self::CLIENT_TB." WHERE email='$email' LIMIT 1";
		$result =self::$db -> query( $query );
		return mysqli_num_rows( $result )>0;
	}
	/**
	 * add client to the database
	 *
	 * @param string  $email    the client's email
	 * @param string  $password the client'password
	 * @return int id of the client
	 */
	public function add_client( $email, $password ) {
		$query ="INSERT INTO ".self::CLIENT_TB." VALUES (NULL,'', '$email','$password','','','', '','')";
		return self::$db -> insert( $query );
	}
	/**
	 * add client to the database
	 *
	 * @param string  $email       the client's email
	 * @param string  $password    the client's password
	 * @param string  $name        the client's name
	 * @param string  $mobile      the client's mobile
	 * @param string  $telephone   the client's telephone
	 * @param string  $address     the client's address
	 * @param string  $notify_rate the client's notification rate
	 * @param string  $pref_map    the client's preferences map to info table
	 * @return int id of the client
	 */
	public function add_client_excess( $email, $password, $name, $mobile, $telephone, $address, $notify_rate, $pref_map ) {
		$query ="INSERT INTO ".self::CLIENT_TB." VALUES (NULL,'$name', '$email','$password','$mobile','$telephone','$address', '$notify_rate','$pref_map')";
		return self::$db -> insert( $query );
	}

	/**
	 * update client info
	 *
	 * @param int     $client_id the client's id
	 * @param string  $name      the client's name
	 * @param string  $mobile    the client's mobile
	 * @param string  $telephone the client's telephone
	 * @param string  $address   the client's address
	 * @return bool true if success, false if failure
	 */
	public function update_client_info( $client_id, $name, $mobile, $telephone, $address ) {
		$query ="UPDATE ".self::CLIENT_TB." SET name='$name', mobile='$mobile', telephone='$telephone', address='$address' WHERE id='$client_id'";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * update client's email
	 *
	 * @param int     $client_id the client's id
	 * @param string  $new_email the client's new email
	 * @return bool true if success, false if failure
	 */
	public function update_email( $client_id, $new_email ) {
		$query ="UPDATE ".self::CLIENT_TB." SET email='$new_email' WHERE id='$client_id'";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * update client's password
	 *
	 * @param int     $client_id    the client's id
	 * @param string  $new_password the client's new password
	 * @return bool true if success, false if failure
	 */
	public function update_password( $client_id, $new_password ) {
		$query ="UPDATE ".self::CLIENT_TB." SET password='$new_password' WHERE id='$client_id'";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * update client's preferences
	 *
	 * @param int     $client_id the client's id
	 * @param string  $new_pref  the client's new preferences map to info table
	 * @return bool true if success, false if failure
	 */
	public function update_preferences( $client_id, $new_pref ) {
		$query ="UPDATE ".self::CLIENT_TB." SET pref_map='$new_pref' WHERE id='$client_id'";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * update client's notification rate
	 *
	 * @param int     $client_id       the client's id
	 * @param string  $new_notify_rate the client's new new notify rate
	 * @return bool true if success, false if failure
	 */
	public function update_notify_rate( $client_id, $new_notify_rate ) {
		$query ="UPDATE ".self::CLIENT_TB." SET notify_rate='$new_notify_rate' WHERE id='$client_id'";
		return self::$db -> query_and_check( $query );
	}






	/*              database info related operations go here          */





	/**
	 * get list of available shopping categories
	 *
	 * @return string list of shopping categories, false otherwise
	 */
	public function get_item_categories() {
		$query ="SELECT DISTINCT category_name FROM ".self::INFO_TB ." WHERE items_count <> 0";
		$result= self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$item_categories=array();
			while ( $row = $result -> fetch_assoc() ) {
				$item_categories[] = $row['category_name'];
			}
			return $item_categories;
		}
		return false;
	}
	/**
	 * get list of available promotion categories
	 *
	 * @return string list of shopping categories, false otherwise
	 */
	public function get_promotion_categories() {
		$query ="SELECT DISTINCT category_name FROM ".self::INFO_TB ." WHERE promotions_count <> 0";
		$result= self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$prom_categories=array();
			while ( $row = $result -> fetch_assoc() ) {
				$prom_categories[] = $row['category_name'];
			}
			return $prom_categories;
		}
		return false;
	}
	/**
	 * get list of available item shopping subcategories (associated with a category)
	 *
	 * @param unknown $category name of category
	 * @return string list of shopping subcategories, false otherwise
	 */
	public function get_item_sub( $category ) {
		$query ="SELECT id, sub_category_name FROM ".self::INFO_TB." WHERE category_name='$category' AND items_count <> 0";
		$result= self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$sub_categories=array();
			while ( $row = $result -> fetch_assoc() ) {
				$sub_categories[] = array_values( $row );
			}
			return $sub_categories;
		}
		return false;
	}
	/**
	 * get list of available promotion shopping subcategories (associated with a category)
	 *
	 * @param unknown $category name of category
	 * @return string list of shopping subcategories, false otherwise
	 */
	public function get_pro_sub( $category ) {
		$query ="SELECT id, sub_category_name FROM ".self::INFO_TB." WHERE category_name='$category' AND promotions_count <> 0";
		$result= self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$sub_categories=array();
			while ( $row = $result -> fetch_assoc() ) {
				$sub_categories[] = array_values( $row );
			}
			return $sub_categories;
		}
		return false;
	}
	/**
	 * get item table name associated with a subcategory
	 *
	 * @param string  $sub_category name of sub category
	 * @return string name of item table if success, false othwewise
	 */
	public function get_item_tb_name( $sub_category ) {
		$query ="SELECT item_tb_name FROM ".self::INFO_TB." WHERE sub_category_name='$sub_category' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['item_tb_name'];
		}
		return false;
	}
	/**
	 * get item table name associated with info table id
	 *
	 * @param int     $info_id info table id
	 * @return string name of item table if success, false othwewise
	 */
	public function get_item_tb_name_by_id( $info_id ) {
		$query ="SELECT item_tb_name FROM ".self::INFO_TB." WHERE id='$info_id' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['item_tb_name'];
		}
		return false;
	}
	/**
	 * get promotion table name associated with a subcategory
	 *
	 * @param string  $sub_category name of sub category
	 * @return string name of promotion table if success, false othwewise
	 */
	public function get_promotion_tb_name( $sub_category ) {
		$query ="SELECT promotion_tb_name FROM ".self::INFO_TB." WHERE sub_category_name='$sub_category' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['promotion_tb_name'];
		}
		return false;
	}
	/**
	 * get item table name associated with info table id
	 *
	 * @param int     $info_id info table id
	 * @return string name of promotion table if success, false othwewise
	 */
	public function get_promotion_tb_name_by_id( $info_id ) {
		$query ="SELECT promotion_tb_name FROM ".self::INFO_TB." WHERE id='$info_id' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['promotion_tb_name'];
		}
		return false;
	}
	/**
	 * get stock table name associated with a subcategory
	 *
	 * @param string  $sub_category name of sub category
	 * @return string name of stock table if success, false othwewise
	 */
	public function get_stock_tb_name( $sub_category ) {
		$query ="SELECT stock_tb_name FROM ".self::INFO_TB." WHERE sub_category_name='$sub_category' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['stock_tb_name'];
		}
		return false;
	}
	/**
	 * get stock table name associated with info table id
	 *
	 * @param int     $info_id info table id
	 * @return string name of stock table if success, false othwewise
	 */
	public function get_stock_tb_name_by_id( $info_id ) {
		$query ="SELECT stock_tb_name FROM ".self::INFO_TB." WHERE id='$info_id' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['stock_tb_name'];
		}
		return false;
	}
	/**
	 * get feature table name associated with info table id
	 *
	 * @param int     $info_id info table id
	 * @return string name of feature table if success, false othwewise
	 */
	public function get_feature_tb_name_by_id( $info_id ) {
		$query ="SELECT feature_tb_name FROM ".self::INFO_TB." WHERE id='$info_id' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['feature_tb_name'];
		}
		return false;
	}
	/**
	 * get key table name associated with info table id
	 *
	 * @param int     $info_id info table id
	 * @return string name of key table if success, false othwewise
	 */
	public function get_key_tb_name_by_id( $info_id ) {
		$query ="SELECT key_tb_name FROM ".self::INFO_TB." WHERE id='$info_id' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['key_tb_name'];
		}
		return false;
	}
	/**
	 * get new learned key table name associated with info table id
	 *
	 * @param int     $info_id info table id
	 * @return string name of learned key table if success, false othwewise
	 */
	public function get_key_learned_tb_name_by_id( $info_id ) {
		$query ="SELECT key_learned_tb_name FROM ".self::INFO_TB." WHERE id='$info_id' LIMIT 1";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return $row['key_learned_tb_name'];
		}
		return false;
	}





	/*              item related operations go here          */





	/**
	 * add item to items watch list
	 *
	 * @param int     $client_id the client's id
	 * @param int     $item_id   the item's id
	 * @param int     $info_id   the item's info talbe id
	 * @param string  $name      the client's defined name
	 * @return query result
	 */
	public function add_item_watch_list( $client_id, $item_id, $info_id, $name ) {
		$query ="INSERT INTO ".self::ITEM_WATCHLIST_TB." VALUES ('$client_id','$item_id','$info_id','0','$name')";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * check items watch list
	 *
	 * @param int     $client_id the client's id
	 * @return query result
	 */
	public function check_item_watch_list( $client_id ) {
		$query ="SELECT fk_item_id, fk_info_id, name, available FROM ".self::ITEM_WATCHLIST_TB." WHERE fk_client_id='$client_id'";
		$result= self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$data=array();
			while ( $row = $result -> fetch_assoc() ) {
				$data = array_merge( $data, array_values( $row ) );
			}
			return $data;
		}
		return false;
	}
	/**
	 * remove item from items watch list
	 *
	 * @param int     $client_id the client's id
	 * @param int     $item_id   the item's id
	 * @param int     $info_id   the item's info talbe id
	 * @return query result
	 */
	public function remove_item_watch_list( $client_id, $item_id, $info_id ) {
		$query ="DELETE FROM ".self::ITEM_WATCHLIST_TB." WHERE fk_client_id='$client_id' AND fk_item_id='$item_id' AND fk_info_id='$info_id'";
		return self::$db -> query( $query );
	}
	/**
	 * get item from items table
	 *
	 * @param int     $item_id item's id
	 * @param string  $info_id info id
	 * @return mixed item
	 */
	public function get_item( $item_id, $info_id ) {
		$item_tb_name=$this -> get_item_tb_name_by_id( $info_id );
		$query ="SELECT * FROM ". $item_tb_name ." WHERE id='$item_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return array_values( $row );
		}
		return false;
	}
	/**
	 * get item name from items table
	 *
	 * @param int     $item_id item's id
	 * @param string  $info_id info id
	 * @return string name
	 */
	public function get_item_name( $item_id, $info_id ) {
		$item_tb_name=$this -> get_item_tb_name_by_id( $info_id );
		$query ="SELECT name FROM ". $item_tb_name ." WHERE id='$item_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row=$result -> fetch_assoc();
			return array_values( $row );
		}
		return false;
	}
	/**
	 * get promotions related to an item
	 *
	 * @param int     $item_id id of item
	 * @param int     $info_id id of info table
	 * @return list id, name and item quanitiy of promotions
	 */
	public function get_item_related_promotions( $item_id, $info_id ) {
		$promotion_tb_name=$this->get_promotion_tb_name_by_id( $info_id );
		$query ="SELECT $promotion_tb_name.fk_promotion_id, promotion.name FROM $promotion_tb_name INNER JOIN promotion ON $promotion_tb_name.fk_promotion_id = promotion.id WHERE $promotion_tb_name.fk_item_id = '$item_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );

			return $rows;
		}
		return false;
	}
	/**
	 * get stores related to an item
	 *
	 * @param int     $item_id id of item
	 * @param int     $info_id id of info table
	 * @return list id, store name and branch name of stores
	 */
	public function get_item_related_stores( $item_id, $info_id ) {
		$stock_tb_name=$this -> get_stock_tb_name_by_id( $info_id );
		$query ="SELECT $stock_tb_name.fk_store_id, store.store_name, store.branch_name, $stock_tb_name.unit_price, $stock_tb_name.quantity FROM $stock_tb_name INNER JOIN store ON $stock_tb_name.fk_store_id = store.id WHERE fk_item_id = '$item_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );
			return $rows;
		}
		return false;
	}
	/**
	 * get stores related to an promotion
	 *
	 * @param int     $promotion_id id of promotion
	 * @return list id, store name and branch name of stores
	 */
	public function get_promotion_related_stores( $promotion_id ) {
		$query ="SELECT ".self::PROMOTION_STORE_TB.".fk_store_id, store.store_name, store.branch_name FROM ".self::PROMOTION_STORE_TB." INNER JOIN store ON ".self::PROMOTION_STORE_TB.".fk_store_id = store.id WHERE fk_promotion_id = '$promotion_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );
			return $rows;
		}
		return false;
	}
	/**
	 * get vendors related to an item
	 *
	 * @param int     $item_id id of item
	 * @param int     $info_id id of info table
	 * @return list id and name of vendors
	 */
	public function get_item_related_vendors( $item_id, $info_id ) {
		$query ="SELECT vendor.id, vendor.name FROM " . self::VENDOR_ITEM_TB ." INNER JOIN vendor ON vendor_item.fk_vendor_id = vendor.id WHERE fk_item_id='$item_id' AND fk_info_id='$info_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );
			return $rows;
		}
		return false;
	}

	/**
	 * get items from item table
	 *
	 * @param string  $info_id info id
	 * @return mixed list of items
	 */
	public function get_items( $info_id ) {
		$item_tb=$this -> get_item_tb_name_by_id( $info_id );
		$query ="SELECT id, name FROM ".$item_tb;
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );
			return $rows;
		}
		return false;
	}
	/**
	 * get searchable items
	 *
	 * @param string  $info_id info id
	 * @return mixed list of features
	 */
	public function get_searchable_items( $info_id ) {
		return array( "3", "Type", "4", "Brand", "5", "Color", "6", "Processor manufactur
", "7", "Processor", "8", "CPU speed
", "9", "Memory size
", "10", "Video Adapter
", "11", "Storage capacity
", "12", "Operating system
", "13", "Screen size
" );
	}
	/**
	 * items custom search
	 *
	 * @param string  $info_id  info id
	 * @param string  $features required features
	 * @return mixed list of items
	 */
	public function custom_item_search( $info_id , $features ) {
		$item_tb=$this -> get_item_tb_name_by_id( $info_id );
		$features=explode( ";", $features );
		$query ="SELECT * FROM ".$item_tb;
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rank=array();
			$temp=count( $features );
			while ( $row = $result -> fetch_assoc() ) {
				$row=array_values( $row );
				$counter=0;
				for ( $i=0; $i < $temp; $i+=2 )
					if ( $row[$features[$i]-1]===$features[$i+1] )
						$counter++;

					if ( $counter > 0 )
						$rank=array_merge( $rank, array( $row[0], $row[1], $counter."" ) );
			}
			return $rank;
		}
		return false;
	}
	/**
	 * smart search
	 *
	 * @param string  $info_id info id
	 * @param string  $input   user's input
	 * @return mixed list of rank
	 */
	public function smart_search( $input ) {
		$rank=array();
		$keywords_found=array();
		$keywords_not_found=array();
		$parser=explode( " ", $input );
		foreach ( $parser as $item ) {
			$query ="SELECT fk_info_id FROM ".self::ITEMS_KEYS." WHERE keyword='$item'";
			$result =self::$db -> query( $query );
			if ( mysqli_num_rows( $result )>0 ) {
				while ( $row = $result -> fetch_assoc() ) {

					$temp=$row['fk_info_id'];
					if ( isset( $rank[$temp] ) ) {
						$rank[$temp]++;
						array_push( $keywords_found[$temp], $item );
					}else {
						$rank[$temp]=1;
						$keywords_found[$temp]=array( $item );
					}


				}
			}else {
				array_push( $keywords_not_found, $item );
			}
		}
		$max_rank=-1;
		$max_id=-1;
		foreach ( $rank as $key => $value ) {
			if ( $value > $max_rank ) {
				$max_rank=$value;
				$max_id=$key;
			}else {
				$keywords_not_found=array_merge( $keywords_not_found, $keywords_found[$key] );
			}
		}
		if ( $max_rank==-1 )
			return array();
		else {
			// return array_merge( array( $max_id, $max_rank ), $keywords_found[$max_id] );
			$insight=$this -> smart_search_insight( $max_id, $keywords_found[$max_id] );
			if ( $insight[0]===-1 )
				return array();
			$name=$this -> get_item_name( $insight[0], $max_id );
			$name=$name[0];

			$this -> learn_new_keys( $max_id, $insight[0], $keywords_not_found );

			return array( $insight[0], $max_id, $name );
		}
	}
	/**
	 * smart search insight
	 *
	 * @param string  $info_id info id
	 * @param string  array  $features insight features matching this info_id
	 * @return mixed list of item rank
	 */
	public function smart_search_insight( $info_id, $features ) {
		$rank=array();
		$key_tb_name=$this -> get_key_tb_name_by_id( $info_id );
		foreach ( $features as $item ) {
			$query ="SELECT fk_item_id FROM $key_tb_name WHERE keyword='$item'";
			$result =self::$db -> query( $query );
			if ( mysqli_num_rows( $result )>0 ) {
				while ( $row = $result -> fetch_assoc() ) {
					$temp=$row['fk_item_id'];
					if ( isset( $rank[$temp] ) )
						$rank[$temp]++;
					else
						$rank[$temp]=1;
				}
			}
		}
		$max_rank=-1;
		$max_id=-1;
		foreach ( $rank as $key => $value ) {
			if ( $rank[$key] > $max_rank ) {
				$max_rank=$rank[$key];
				$max_id=$key;
			}
		}
		return array( $max_id, $max_rank );
	}
	/**
	 * learn new keys
	 *
	 * @param string  $info_id info id
	 * @param string  item_id  item id
	 * @param list    keywords_not_found  keywords that did not match any category
	 * @return void
	 */
	public function learn_new_keys( $info_id, $item_id, $keywords_not_found ) {
		$learned_tb=$this -> get_key_learned_tb_name_by_id( $info_id );
		foreach ( $keywords_not_found as $key => $value ) {
			$query ="UPDATE $learned_tb SET count=count+1 WHERE fk_item_id='$item_id' AND keyword='$value' LIMIT 1";
			if ( self::$db -> query_and_check( $query ) === false) {
				$query ="INSERT INTO $learned_tb VALUES ('$value','0','$item_id')";
				self::$db -> query( $query );
			}
		}
	}
	/**
	 * add learned key
	 *
	 * @param string  $info_id info id
	 * @param string  item_id  item id
	 * @param list    keywords  the new learned keyword
	 * @return void
	 */
	public function add_learned_key( $info_id, $item_id, $keyword ) {
		$key_tb=$this -> get_key_tb_name_by_id( $info_id );
		$query ="INSERT INTO $key_tb VALUES ('$item_id','$keyword')";
		self::$db -> query( $query );
		$query ="INSERT INTO ".self::ITEMS_KEYS." VALUES ('$info_id','$keyword')";
		self::$db -> query( $query );

	}




	/*              store related operations go here          */





	/**
	 * get store from stores table
	 *
	 * @param int     $store_id store's id
	 * @return mixed store
	 */
	public function get_store( $store_id ) {
		$query ="SELECT * FROM ".self::STORE_TB." WHERE id='$store_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row = $result -> fetch_assoc();
			return array_values( $row );
		}
		return false;
	}
	/**
	 * get stores from database
	 *
	 * @return mixed list of stores
	 */
	public function get_stores() {
		$query ="SELECT id, store_name, branch_name FROM ".self::STORE_TB;
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows = array_merge( $rows, array_values( $row ) );
			return $rows;
		}
		return false;
	}
	/**
	 * get items by a store
	 *
	 * @param int     $store_id id of store
	 * @return mixed list of categories-subcategories and ids
	 */
	public function get_store_related_items( $store_id ) {
		$query ="SELECT items_tb_map FROM ".self::STORE_TB." WHERE id='$store_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row = $result -> fetch_assoc();
			$info_ids = explode( ";", $row['items_tb_map'] );
			$items=array();
			include 'db_info.php';
			foreach ( $info_ids as $id )
				$items=array_merge( $items, array( $category_name[$id], $sub_category_name[$id], $id ) );

			return $items;
		}
		return false;
	}
	/**
	 * get promotions by a store
	 *
	 * @param int     $store_id id of store
	 * @return mixed list of id and name of promotions
	 */
	public function get_store_related_promotions( $store_id ) {
		$query ="SELECT promotion_store.fk_promotion_id, promotion.name FROM ".self::PROMOTION_STORE_TB." INNER JOIN promotion ON promotion_store.fk_promotion_id=promotion.id WHERE fk_store_id='$store_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows = array_merge( $rows, array_values( $row ) );
			return $rows;
		}
		return false;
	}







	/*              promotion related operations go here          */





	/**
	 * add promotion to promotions watch list
	 *
	 * @param int     $client_id the client's id
	 * @param int     $item_id   the item's id
	 * @param int     $info_id   the item's info talbe id
	 * @param string  $name      the client's defined name
	 * @return query result
	 */
	public function add_promotion_watch_list( $client_id, $item_id, $info_id, $name ) {
		$query ="INSERT INTO ".self::PROMOTION_WATCHLIST_TB." VALUES ('$client_id','$item_id','$info_id','0','$name')";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * check promotions watch list
	 *
	 * @param int     $client_id the client's id
	 * @return query result
	 */
	public function check_promotion_watch_list( $client_id ) {
		$query ="SELECT  fk_item_id, fk_info_id, name, available  FROM ".self::PROMOTION_WATCHLIST_TB." WHERE fk_client_id='$client_id'";
		$result= self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$data=array();
			while ( $row = $result -> fetch_assoc() ) {
				$data = array_merge( $data, array_values( $row ) );
			}
			return $data;
		}
		return false;
	}
	/**
	 * remove promotion from promotions watch list
	 *
	 * @param int     $client_id the client's id
	 * @param int     $item_id   the item's id
	 * @param int     $info_id   the item's info talbe id
	 * @return bool true if success
	 */
	public function remove_promotion_watch_list( $client_id, $item_id, $info_id ) {
		$query ="DELETE FROM ".self::PROMOTION_WATCHLIST_TB." WHERE fk_client_id='$client_id' AND fk_item_id='$item_id' AND fk_info_id='$info_id'";
		return self::$db -> query_and_check( $query );
	}
	/**
	 * get promotions from a promotion table
	 *
	 * @param string  $info_id info id
	 * @return list id and name of promotions
	 */
	public function get_promotions( $info_id ) {
		$promotion_tb=$this -> get_promotion_tb_name_by_id( $info_id );
		$query ="SELECT $promotion_tb.fk_promotion_id, promotion.name FROM $promotion_tb INNER JOIN promotion ON $promotion_tb.fk_promotion_id = promotion.id";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );

			return $rows;
		}
		return false;
	}
	/**
	 * get promotion details
	 *
	 * @param promotion_id $promotion_id id of promotion
	 * @return mixed promotion
	 */
	public function get_promotion( $promotion_id ) {
		$query ="SELECT * FROM ".self::PROMOTION_TB." WHERE id='$promotion_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$row = $result -> fetch_assoc();
			$promotion=array();
			$promotion['d']=array( $row['name'], $row['start_in'], $row['end_in'], $row['price'] );
			$info_ids = explode( ";", $row['items_tb_map'] );
			$promotion['i']=array();
			include 'db_info.php';
			foreach ( $info_ids as $id ) {
				$pro_tb_name=$promotion_tb_name[$id];
				$itm_tb_name=$item_tb_name[$id];

				$query="SELECT $pro_tb_name.fk_item_id, $itm_tb_name.name, $pro_tb_name.quantity FROM $pro_tb_name INNER JOIN $itm_tb_name ON $pro_tb_name.fk_item_id=$itm_tb_name.id WHERE fk_promotion_id='$promotion_id'";
				$result =self::$db -> query( $query );
				if ( mysqli_num_rows( $result )>0 ) {
					while ( $row = $result -> fetch_assoc() ) {
						$promotion['i']=array_merge( $promotion['i'], array_values( $row ) );
						$promotion['i'][]=$id;
					}
				}
			}
			return $promotion;
		}
		return false;
	}





	/*              vendor related operations go here          */




	/**
	 * get vendor from vendors table
	 *
	 * @param int     $id vendor's id
	 * @return mixed vendor
	 */
	public function get_vendor( $id ) {
		$query ="SELECT * FROM ".self::VENDOR_TB." WHERE id='$id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows[] = array_values( $row );

			return $rows;
		}
		return false;
	}

	/*              column related operations go here          */




	/**
	 * get column names of a specific item table
	 *
	 * @param int     $info_id info table's id
	 * @return list string column names
	 */
	public function get_item_column_names( $info_id ) {
		$query ="SELECT name FROM ".self::COLUMN_NAMES_TB." WHERE fk_info_id='$info_id'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows =array_merge( $rows, array_values( $row ) );

			return $rows;
		}
		return false;
	}
	/**
	 * get column features of a specific item table
	 *
	 * @param int     $info_id info table's id
	 * @return list string column features
	 */
	public function get_item_column_features( $info_id , $column_index ) {
		$feature_tb_name=$this -> get_feature_tb_name_by_id( $info_id );
		$query ="SELECT value FROM $feature_tb_name WHERE id='$column_index'";
		$result =self::$db -> query( $query );
		if ( mysqli_num_rows( $result )>0 ) {
			$rows=array();
			while ( $row = $result -> fetch_assoc() )
				$rows = array_merge( $rows, array_values( $row ) );

			return $rows;
		}
		return false;
	}

}
