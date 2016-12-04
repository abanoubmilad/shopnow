package com.shopnow.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.shopnow.obj.CustomSearchEntry;
import com.shopnow.obj.IDItemEntry;
import com.shopnow.obj.ItemEntry;
import com.shopnow.obj.ItemRankEntry;
import com.shopnow.obj.Promotion;
import com.shopnow.obj.PromotionItemEntry;
import com.shopnow.obj.StoreEntry;
import com.shopnow.obj.WatchListEntry;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

public class TaskManager {
	private final static ConnectManager connectManager = ConnectManager
			.getInstance();
	public static int status = -1;

	private static final String URL_HOST = "http://abanoubcs.webatu.com/shop_now/m/";
	private static final String URL_add_item_watch_list = URL_HOST
			+ "add_item_watch_list.php";
	private static final String URL_get_promotions = URL_HOST
			+ "get_promotions.php";
	private static final String URL_add_promotion_watch_list = URL_HOST
			+ "add_promotion_watch_list.php";
	private static final String URL_get_store = URL_HOST + "get_store.php";
	private static final String URL_get_store_related_items = URL_HOST
			+ "get_store_related_items.php";
	private static final String URL_get_store_related_promotions = URL_HOST
			+ "get_store_related_promotions.php";
	private static final String URL_get_stores = URL_HOST + "get_stores.php";
	private static final String URL_get_item_cat = URL_HOST
			+ "get_item_cat.php";
	private static final String URL_get_item_sub = URL_HOST
			+ "get_item_sub.php";
	private static final String URL_get_pro_sub = URL_HOST + "get_pro_sub.php";
	private static final String URL_get_item_column_names = URL_HOST
			+ "get_item_column_names.php";
	private static final String URL_get_item_column_features = URL_HOST
			+ "get_item_column_features.php";
	private static final String URL_get_vendor = URL_HOST + "get_vendor.php";
	private static final String URL_get_item = URL_HOST + "get_item.php";
	private static final String URL_get_item_related_promotions = URL_HOST
			+ "get_item_related_promotions.php";
	private static final String URL_remove_item_watch_list = URL_HOST
			+ "remove_item_watch_list.php";
	private static final String URL_check_item_watch_list = URL_HOST
			+ "check_item_watch_list.php";
	private static final String URL_check_promotion_watch_list = URL_HOST
			+ "check_promotion_watch_list.php";
	private static final String URL_get_item_related_stores = URL_HOST
			+ "get_item_related_stores.php";
	private static final String URL_get_promotion_related_stores = URL_HOST
			+ "get_promotion_related_stores.php";
	private static final String URL_remove_promotion_watch_list = URL_HOST
			+ "remove_promotion_watch_list.php";
	private static final String URL_get_item_related_vendors = URL_HOST
			+ "get_item_related_vendors.php";
	private static final String URL_sign_in = URL_HOST + "sign_in.php";
	private static final String URL_get_items = URL_HOST + "get_items.php";
	private static final String URL_sign_up = URL_HOST + "sign_up.php";
	private static final String URL_get_pro_cat = URL_HOST + "get_pro_cat.php";
	private static final String URL_get_promotion = URL_HOST
			+ "get_promotion.php";
	private static final String URL_custom_item_search = URL_HOST
			+ "custom_item_search.php";
	private static final String URL_smart_search = URL_HOST
			+ "smart_search.php";
	private static final String URL_get_searchable_features = URL_HOST
			+ "get_searchable_features.php";

	public static void signIn(String email, String password) {
		RequestBody formBody = new FormEncodingBuilder().add("email", email)
				.add("pass", password).build();

		JSONObject json = connectManager.request(URL_sign_in, formBody);
		try {
			TaskManager.status = json.getInt("s");
		} catch (Exception e) {
			TaskManager.status = -1;
		}
	}

	public static void signUp(String name, String email, String password) {
		RequestBody formBody = new FormEncodingBuilder().add("name", name)
				.add("email", email).add("pass", password).build();

		JSONObject json = connectManager.request(URL_sign_up, formBody);
		try {
			TaskManager.status = json.getInt("s");
		} catch (Exception e) {
			TaskManager.status = -1;
		}
	}

	public static ArrayList<String> getItemCategories() {
		RequestBody formBody = new FormEncodingBuilder().add("0", "0").build();

		JSONObject json = connectManager.request(URL_get_item_cat, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("c");
				ArrayList<String> cats = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++)
					cats.add(arr.getString(i));
				return cats;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<String> getPromotionCategories() {
		RequestBody formBody = new FormEncodingBuilder().add("0", "0").build();

		JSONObject json = connectManager.request(URL_get_pro_cat, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("c");
				ArrayList<String> cats = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++)
					cats.add(arr.getString(i));
				return cats;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<ItemEntry> getItemSubCategories(String categrory) {
		RequestBody formBody = new FormEncodingBuilder().add("cat", categrory)
				.build();

		JSONObject json = connectManager.request(URL_get_item_sub, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("c");
				ArrayList<ItemEntry> subs = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					subs.add(new ItemEntry(inArr.getString(0), inArr
							.getString(1)));
				}
				return subs;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<ItemEntry> getPromotionSubCategories(
			String categrory) {
		RequestBody formBody = new FormEncodingBuilder().add("cat", categrory)
				.build();

		JSONObject json = connectManager.request(URL_get_pro_sub, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("c");
				ArrayList<ItemEntry> subs = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					subs.add(new ItemEntry(inArr.getString(0), inArr
							.getString(1)));
				}
				return subs;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<ItemEntry> getItems(String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("info_id", info_id).build();
		JSONObject json = connectManager.request(URL_get_items, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("i");
				ArrayList<ItemEntry> items = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					items.add(new ItemEntry(inArr.getString(0), inArr
							.getString(1)));
				}
				return items;
			}
			return null;

		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	// public static ArrayList<ItemEntry> get_searchable_items(String info_id,
	// String features) {
	// RequestBody formBody = new FormEncodingBuilder()
	// .add("features", features).add("info_id", info_id).build();
	// JSONObject json = connectManager.request(URL_custom_item_search,
	// formBody);
	// try {
	// TaskManager.status = json.getInt("s");
	// if (TaskManager.status == 7) {
	// JSONArray arr = json.getJSONArray("i");
	// ArrayList<ItemRankEntry> items = new ArrayList<>(
	// arr.length() / 3);
	// for (int i = 0; i < arr.length(); i += 3) {
	// items.add(new ItemRankEntry(arr.getString(i), arr
	// .getString(i + 1), Integer.parseInt(arr
	// .getString(i + 2))));
	// }
	// return items;
	// }
	// return null;
	//
	// } catch (Exception e) {
	// TaskManager.status = -1;
	// return null;
	// }
	// }
	public static ArrayList<ItemRankEntry> customSearch(String info_id,
			String features) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("features", features).add("info_id", info_id).build();
		JSONObject json = connectManager.request(URL_custom_item_search,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("i");
				ArrayList<ItemRankEntry> items = new ArrayList<>(
						arr.length() / 3);
				for (int i = 0; i < arr.length(); i += 3) {
					items.add(new ItemRankEntry(arr.getString(i), arr
							.getString(i + 1), Integer.parseInt(arr
							.getString(i + 2))));
				}
				return items;
			}
			return null;

		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static IDItemEntry smartSearch(String input) {
		RequestBody formBody = new FormEncodingBuilder().add("input", input)
				.build();
		JSONObject json = connectManager.request(URL_smart_search, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("r");
				if (arr.length() == 0) {
					TaskManager.status = 6;
					return null;
				} else {
					return new IDItemEntry(arr.getString(0), arr.getString(1),
							arr.getString(2));
				}

			}
			return null;

		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<ItemEntry> getPromotions(String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("info_id", info_id).build();

		JSONObject json = connectManager.request(URL_get_promotions, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("p");
				ArrayList<ItemEntry> promotions = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					promotions.add(new ItemEntry(inArr.getString(0), inArr
							.getString(1)));
				}
				return promotions;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<String> getItem(String item_id, String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id).build();

		JSONObject json = connectManager.request(URL_get_item, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("i");
				ArrayList<String> item = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					item.add(arr.getString(i));
				}
				return item;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<CustomSearchEntry> getSearchableFeatures(
			String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("info_id", info_id).build();

		JSONObject json = connectManager.request(URL_get_searchable_features,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("f");
				ArrayList<CustomSearchEntry> items = new ArrayList<>(
						arr.length() / 2);
				for (int i = 0; i < arr.length(); i += 2)
					items.add(new CustomSearchEntry(arr.getString(i), arr
							.getString(i + 1)));
				return items;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<String> getItemColumnNames(String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("info_id", info_id).build();

		JSONObject json = connectManager.request(URL_get_item_column_names,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("n");
				ArrayList<String> items = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++)
					items.add(arr.getString(i));

				return items;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static String[] getItemColumnFeatures(String info_id,
			String column_index) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("info_id", info_id).add("column_index", column_index)
				.build();

		JSONObject json = connectManager.request(URL_get_item_column_features,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("f");
				String[] names = new String[arr.length() + 1];
				names[0] = "any";
				for (int i = 0, e = 1; i < arr.length(); i++, e++)
					names[e] = arr.getString(i);

				return names;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<StoreEntry> getStores() {
		RequestBody formBody = new FormEncodingBuilder().add("0", "0").build();

		JSONObject json = connectManager.request(URL_get_stores, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("r");
				ArrayList<StoreEntry> stores = new ArrayList<>(arr.length() / 3);
				for (int i = 0; i < arr.length(); i += 3)
					stores.add(new StoreEntry(arr.getString(i), arr
							.getString(i + 1), arr.getString(i + 2)));

				return stores;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<StoreEntry> getPromotionRelatedStores(
			String promotion_id) {
		RequestBody formBody = new FormEncodingBuilder().add("promotion_id",
				promotion_id).build();
		JSONObject json = connectManager.request(
				URL_get_promotion_related_stores, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("t");
				ArrayList<StoreEntry> stores = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					stores.add(new StoreEntry(inArr.getString(0), inArr
							.getString(1), inArr.getString(2)));
				}
				return stores;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<StoreEntry> getItemRelatedStores(String item_id,
			String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id).build();
		JSONObject json = connectManager.request(URL_get_item_related_stores,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("t");
				ArrayList<StoreEntry> stores = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					stores.add(new StoreEntry(inArr.getString(0), inArr
							.getString(1), inArr.getString(2)));
				}
				return stores;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static Promotion getPromotion(String promotion_id) {
		RequestBody formBody = new FormEncodingBuilder().add("promotion_id",
				promotion_id).build();
		Log.i("id here", promotion_id);
		JSONObject json = connectManager.request(URL_get_promotion, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				json = json.getJSONObject("p");
				JSONArray arr = json.getJSONArray("d");
				Promotion promotion = new Promotion(arr.getString(0),
						arr.getString(1), arr.getString(2), arr.getString(3),
						new ArrayList<PromotionItemEntry>());
				ArrayList<PromotionItemEntry> itemsArr = promotion
						.getPromotionItems();
				arr = json.getJSONArray("i");
				Log.i("the json i is ", json.toString());
				for (int i = 0; i < arr.length(); i += 4)
					itemsArr.add(new PromotionItemEntry(arr.getString(i), arr
							.getString(i + 3), arr.getString(i + 1), arr
							.getString(i + 2)));

				return promotion;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<ItemEntry> getItemRelatedPromotions(String item_id,
			String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id).build();

		JSONObject json = connectManager.request(
				URL_get_item_related_promotions, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("p");
				ArrayList<ItemEntry> promotions = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++) {
					JSONArray inArr = arr.getJSONArray(i);
					promotions.add(new ItemEntry(inArr.getString(0), inArr
							.getString(1)));
				}
				return promotions;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static HashMap<String, List<ItemEntry>> getStoreRelatedItems(
			String store_id) {
		RequestBody formBody = new FormEncodingBuilder().add("store_id",
				store_id).build();

		JSONObject json = connectManager.request(URL_get_store_related_items,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("i");
				HashMap<String, List<ItemEntry>> map = new HashMap<String, List<ItemEntry>>();
				for (int i = 0; i < arr.length(); i += 3) {
					String cat = arr.getString(i);
					if (!map.containsKey(cat))
						map.put(cat, new ArrayList<ItemEntry>());
					map.get(cat).add(
							new ItemEntry(arr.getString(i + 2), arr
									.getString(i + 1)));

				}
				return map;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<ItemEntry> getStoreRelatedPromotions(String store_id) {
		RequestBody formBody = new FormEncodingBuilder().add("store_id",
				store_id).build();

		JSONObject json = connectManager.request(
				URL_get_store_related_promotions, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("p");
				ArrayList<ItemEntry> promotions = new ArrayList<>(
						arr.length() / 2);
				for (int i = 0; i < arr.length(); i += 2) {
					promotions.add(new ItemEntry(arr.getString(i), arr
							.getString(i + 1)));
				}
				return promotions;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<String> getStore(String store_id) {
		RequestBody formBody = new FormEncodingBuilder().add("store_id",
				store_id).build();

		JSONObject json = connectManager.request(URL_get_store, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {

				JSONArray arr = json.getJSONArray("r");
				ArrayList<String> store = new ArrayList<>(arr.length());
				for (int i = 0; i < arr.length(); i++)
					store.add(arr.getString(i));
				return store;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static String getPreferences() {
		RequestBody formBody = new FormEncodingBuilder().add("0", "0").build();

		JSONObject json = connectManager.request(URL_get_store, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7)
				return json.getString("p");
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static boolean updatePreferences(String pref) {
		RequestBody formBody = new FormEncodingBuilder().add("pref", pref)
				.build();

		JSONObject json = connectManager.request(URL_get_store, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7)
				return true;
			return false;
		} catch (Exception e) {
			TaskManager.status = -1;
			return false;
		}
	}

	public static boolean addItemWatchlist(String item_id, String info_id,
			String name) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id)
				.add("name", name).build();

		JSONObject json = connectManager.request(URL_add_item_watch_list,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7)
				return true;
			return false;
		} catch (Exception e) {
			TaskManager.status = -1;
			return false;
		}
	}

	public static boolean removeItemWatchlist(String item_id, String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id).build();

		JSONObject json = connectManager.request(URL_remove_item_watch_list,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7)
				return true;
			return false;
		} catch (Exception e) {
			TaskManager.status = -1;
			return false;
		}
	}

	public static boolean addPromotionWatchlist(String item_id, String info_id,
			String name) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id)
				.add("name", name).build();

		JSONObject json = connectManager.request(URL_add_promotion_watch_list,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7)
				return true;
			return false;
		} catch (Exception e) {
			TaskManager.status = -1;
			return false;
		}
	}

	public static boolean removePromotionWatchlist(String item_id,
			String info_id) {
		RequestBody formBody = new FormEncodingBuilder()
				.add("item_id", item_id).add("info_id", info_id).build();

		JSONObject json = connectManager.request(
				URL_remove_promotion_watch_list, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7)
				return true;
			return false;
		} catch (Exception e) {
			TaskManager.status = -1;
			return false;
		}
	}

	public static ArrayList<WatchListEntry> checkPromotionWatchlist() {
		RequestBody formBody = new FormEncodingBuilder().add("0", "0").build();

		JSONObject json = connectManager.request(
				URL_check_promotion_watch_list, formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("c");
				ArrayList<WatchListEntry> itemsArr = new ArrayList<>(
						arr.length() / 4);
				for (int i = 0; i < arr.length(); i += 4)
					itemsArr.add(new WatchListEntry(arr.getString(i), arr
							.getString(i + 1), arr.getString(i + 2), arr
							.getString(i + 3).equals("1")));
				return itemsArr;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

	public static ArrayList<WatchListEntry> checkItemWatchlist() {
		RequestBody formBody = new FormEncodingBuilder().add("0", "0").build();

		JSONObject json = connectManager.request(URL_check_item_watch_list,
				formBody);
		try {
			TaskManager.status = json.getInt("s");
			if (TaskManager.status == 7) {
				JSONArray arr = json.getJSONArray("c");
				ArrayList<WatchListEntry> itemsArr = new ArrayList<>(
						arr.length() / 4);
				for (int i = 0; i < arr.length(); i += 4)
					itemsArr.add(new WatchListEntry(arr.getString(i), arr
							.getString(i + 1), arr.getString(i + 2), arr
							.getString(i + 3).equals("1")));
				return itemsArr;
			}
			return null;
		} catch (Exception e) {
			TaskManager.status = -1;
			return null;
		}
	}

}