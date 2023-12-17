package com.example.fyps.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HelpDatabase_Impl extends HelpDatabase {
  private volatile QuizHistoryDao _quizHistoryDao;

  private volatile RewardHistoryDao _rewardHistoryDao;

  private volatile RewardDao _rewardDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(8) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `quiz_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `materialName` TEXT NOT NULL, `setName` TEXT NOT NULL, `score` TEXT NOT NULL, `date` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reward_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userDocId` TEXT NOT NULL, `redeemedDate` INTEGER NOT NULL, `rewardName` TEXT NOT NULL, `rewardDetails` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reward` (`rewardName` TEXT NOT NULL, `imageBytes` BLOB, `redeemLimit` INTEGER NOT NULL, `redeemedCount` INTEGER NOT NULL, `rewardDescription` TEXT NOT NULL, `rewardPoints` INTEGER NOT NULL, `isAdded` INTEGER NOT NULL, `isUpdated` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `imageUrl` TEXT, PRIMARY KEY(`rewardName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '88d8102d8511008b458532cc676368a5')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `quiz_history`");
        _db.execSQL("DROP TABLE IF EXISTS `reward_history`");
        _db.execSQL("DROP TABLE IF EXISTS `reward`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsQuizHistory = new HashMap<String, TableInfo.Column>(6);
        _columnsQuizHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizHistory.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizHistory.put("materialName", new TableInfo.Column("materialName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizHistory.put("setName", new TableInfo.Column("setName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizHistory.put("score", new TableInfo.Column("score", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizHistory.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuizHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuizHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuizHistory = new TableInfo("quiz_history", _columnsQuizHistory, _foreignKeysQuizHistory, _indicesQuizHistory);
        final TableInfo _existingQuizHistory = TableInfo.read(_db, "quiz_history");
        if (! _infoQuizHistory.equals(_existingQuizHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "quiz_history(com.example.fyps.database.QuizHistory).\n"
                  + " Expected:\n" + _infoQuizHistory + "\n"
                  + " Found:\n" + _existingQuizHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsRewardHistory = new HashMap<String, TableInfo.Column>(5);
        _columnsRewardHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardHistory.put("userDocId", new TableInfo.Column("userDocId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardHistory.put("redeemedDate", new TableInfo.Column("redeemedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardHistory.put("rewardName", new TableInfo.Column("rewardName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardHistory.put("rewardDetails", new TableInfo.Column("rewardDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRewardHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRewardHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRewardHistory = new TableInfo("reward_history", _columnsRewardHistory, _foreignKeysRewardHistory, _indicesRewardHistory);
        final TableInfo _existingRewardHistory = TableInfo.read(_db, "reward_history");
        if (! _infoRewardHistory.equals(_existingRewardHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "reward_history(com.example.fyps.database.RewardHistory).\n"
                  + " Expected:\n" + _infoRewardHistory + "\n"
                  + " Found:\n" + _existingRewardHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsReward = new HashMap<String, TableInfo.Column>(10);
        _columnsReward.put("rewardName", new TableInfo.Column("rewardName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("imageBytes", new TableInfo.Column("imageBytes", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("redeemLimit", new TableInfo.Column("redeemLimit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("redeemedCount", new TableInfo.Column("redeemedCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("rewardDescription", new TableInfo.Column("rewardDescription", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("rewardPoints", new TableInfo.Column("rewardPoints", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("isAdded", new TableInfo.Column("isAdded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("isUpdated", new TableInfo.Column("isUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReward.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReward = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReward = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReward = new TableInfo("reward", _columnsReward, _foreignKeysReward, _indicesReward);
        final TableInfo _existingReward = TableInfo.read(_db, "reward");
        if (! _infoReward.equals(_existingReward)) {
          return new RoomOpenHelper.ValidationResult(false, "reward(com.example.fyps.database.Reward).\n"
                  + " Expected:\n" + _infoReward + "\n"
                  + " Found:\n" + _existingReward);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "88d8102d8511008b458532cc676368a5", "7a328d86525518d78fd29ab1813f219f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "quiz_history","reward_history","reward");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `quiz_history`");
      _db.execSQL("DELETE FROM `reward_history`");
      _db.execSQL("DELETE FROM `reward`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(QuizHistoryDao.class, QuizHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RewardHistoryDao.class, RewardHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RewardDao.class, RewardDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public QuizHistoryDao quizHistoryDao() {
    if (_quizHistoryDao != null) {
      return _quizHistoryDao;
    } else {
      synchronized(this) {
        if(_quizHistoryDao == null) {
          _quizHistoryDao = new QuizHistoryDao_Impl(this);
        }
        return _quizHistoryDao;
      }
    }
  }

  @Override
  public RewardHistoryDao rewardHistoryDao() {
    if (_rewardHistoryDao != null) {
      return _rewardHistoryDao;
    } else {
      synchronized(this) {
        if(_rewardHistoryDao == null) {
          _rewardHistoryDao = new RewardHistoryDao_Impl(this);
        }
        return _rewardHistoryDao;
      }
    }
  }

  @Override
  public RewardDao rewardDao() {
    if (_rewardDao != null) {
      return _rewardDao;
    } else {
      synchronized(this) {
        if(_rewardDao == null) {
          _rewardDao = new RewardDao_Impl(this);
        }
        return _rewardDao;
      }
    }
  }
}
