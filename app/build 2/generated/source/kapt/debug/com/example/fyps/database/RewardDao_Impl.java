package com.example.fyps.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RewardDao_Impl implements RewardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Reward> __insertionAdapterOfReward;

  private final EntityDeletionOrUpdateAdapter<Reward> __updateAdapterOfReward;

  private final SharedSQLiteStatement __preparedStmtOfIncrementRedeemedCount;

  public RewardDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReward = new EntityInsertionAdapter<Reward>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `reward` (`rewardName`,`imageBytes`,`redeemLimit`,`redeemedCount`,`rewardDescription`,`rewardPoints`,`isAdded`,`isUpdated`,`isDeleted`,`imageUrl`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Reward value) {
        if (value.getRewardName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRewardName());
        }
        if (value.getImageBytes() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getImageBytes());
        }
        stmt.bindLong(3, value.getRedeemLimit());
        stmt.bindLong(4, value.getRedeemedCount());
        if (value.getRewardDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRewardDescription());
        }
        stmt.bindLong(6, value.getRewardPoints());
        stmt.bindLong(7, value.isAdded());
        stmt.bindLong(8, value.isUpdated());
        stmt.bindLong(9, value.isDeleted());
        if (value.getImageUrl() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getImageUrl());
        }
      }
    };
    this.__updateAdapterOfReward = new EntityDeletionOrUpdateAdapter<Reward>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `reward` SET `rewardName` = ?,`imageBytes` = ?,`redeemLimit` = ?,`redeemedCount` = ?,`rewardDescription` = ?,`rewardPoints` = ?,`isAdded` = ?,`isUpdated` = ?,`isDeleted` = ?,`imageUrl` = ? WHERE `rewardName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Reward value) {
        if (value.getRewardName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRewardName());
        }
        if (value.getImageBytes() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getImageBytes());
        }
        stmt.bindLong(3, value.getRedeemLimit());
        stmt.bindLong(4, value.getRedeemedCount());
        if (value.getRewardDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRewardDescription());
        }
        stmt.bindLong(6, value.getRewardPoints());
        stmt.bindLong(7, value.isAdded());
        stmt.bindLong(8, value.isUpdated());
        stmt.bindLong(9, value.isDeleted());
        if (value.getImageUrl() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getImageUrl());
        }
        if (value.getRewardName() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getRewardName());
        }
      }
    };
    this.__preparedStmtOfIncrementRedeemedCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE reward SET redeemedCount = redeemedCount + 1 WHERE rewardName = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Reward reward, final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfReward.insertAndReturnId(reward);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final Reward reward, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReward.handle(reward);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object incrementRedeemedCount(final String rewardName,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementRedeemedCount.acquire();
        int _argIndex = 1;
        if (rewardName == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, rewardName);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfIncrementRedeemedCount.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getRewardByName(final String name,
      final Continuation<? super Reward> continuation) {
    final String _sql = "SELECT * FROM reward WHERE rewardName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Reward>() {
      @Override
      public Reward call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfImageBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "imageBytes");
          final int _cursorIndexOfRedeemLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemLimit");
          final int _cursorIndexOfRedeemedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedCount");
          final int _cursorIndexOfRewardDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardDescription");
          final int _cursorIndexOfRewardPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardPoints");
          final int _cursorIndexOfIsAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdded");
          final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "isUpdated");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final Reward _result;
          if(_cursor.moveToFirst()) {
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final byte[] _tmpImageBytes;
            if (_cursor.isNull(_cursorIndexOfImageBytes)) {
              _tmpImageBytes = null;
            } else {
              _tmpImageBytes = _cursor.getBlob(_cursorIndexOfImageBytes);
            }
            final int _tmpRedeemLimit;
            _tmpRedeemLimit = _cursor.getInt(_cursorIndexOfRedeemLimit);
            final int _tmpRedeemedCount;
            _tmpRedeemedCount = _cursor.getInt(_cursorIndexOfRedeemedCount);
            final String _tmpRewardDescription;
            if (_cursor.isNull(_cursorIndexOfRewardDescription)) {
              _tmpRewardDescription = null;
            } else {
              _tmpRewardDescription = _cursor.getString(_cursorIndexOfRewardDescription);
            }
            final int _tmpRewardPoints;
            _tmpRewardPoints = _cursor.getInt(_cursorIndexOfRewardPoints);
            final int _tmpIsAdded;
            _tmpIsAdded = _cursor.getInt(_cursorIndexOfIsAdded);
            final int _tmpIsUpdated;
            _tmpIsUpdated = _cursor.getInt(_cursorIndexOfIsUpdated);
            final int _tmpIsDeleted;
            _tmpIsDeleted = _cursor.getInt(_cursorIndexOfIsDeleted);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _result = new Reward(_tmpRewardName,_tmpImageBytes,_tmpRedeemLimit,_tmpRedeemedCount,_tmpRewardDescription,_tmpRewardPoints,_tmpIsAdded,_tmpIsUpdated,_tmpIsDeleted,_tmpImageUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllRewards(final Continuation<? super List<Reward>> continuation) {
    final String _sql = "SELECT * FROM reward WHERE isDeleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Reward>>() {
      @Override
      public List<Reward> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfImageBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "imageBytes");
          final int _cursorIndexOfRedeemLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemLimit");
          final int _cursorIndexOfRedeemedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedCount");
          final int _cursorIndexOfRewardDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardDescription");
          final int _cursorIndexOfRewardPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardPoints");
          final int _cursorIndexOfIsAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdded");
          final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "isUpdated");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<Reward> _result = new ArrayList<Reward>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Reward _item;
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final byte[] _tmpImageBytes;
            if (_cursor.isNull(_cursorIndexOfImageBytes)) {
              _tmpImageBytes = null;
            } else {
              _tmpImageBytes = _cursor.getBlob(_cursorIndexOfImageBytes);
            }
            final int _tmpRedeemLimit;
            _tmpRedeemLimit = _cursor.getInt(_cursorIndexOfRedeemLimit);
            final int _tmpRedeemedCount;
            _tmpRedeemedCount = _cursor.getInt(_cursorIndexOfRedeemedCount);
            final String _tmpRewardDescription;
            if (_cursor.isNull(_cursorIndexOfRewardDescription)) {
              _tmpRewardDescription = null;
            } else {
              _tmpRewardDescription = _cursor.getString(_cursorIndexOfRewardDescription);
            }
            final int _tmpRewardPoints;
            _tmpRewardPoints = _cursor.getInt(_cursorIndexOfRewardPoints);
            final int _tmpIsAdded;
            _tmpIsAdded = _cursor.getInt(_cursorIndexOfIsAdded);
            final int _tmpIsUpdated;
            _tmpIsUpdated = _cursor.getInt(_cursorIndexOfIsUpdated);
            final int _tmpIsDeleted;
            _tmpIsDeleted = _cursor.getInt(_cursorIndexOfIsDeleted);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _item = new Reward(_tmpRewardName,_tmpImageBytes,_tmpRedeemLimit,_tmpRedeemedCount,_tmpRewardDescription,_tmpRewardPoints,_tmpIsAdded,_tmpIsUpdated,_tmpIsDeleted,_tmpImageUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object countByName(final String rewardName,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM reward WHERE rewardName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (rewardName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rewardName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object countByNameExcludingCurrent(final String newRewardName,
      final String currentRewardName, final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM reward WHERE rewardName = ? AND rewardName != ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (newRewardName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, newRewardName);
    }
    _argIndex = 2;
    if (currentRewardName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, currentRewardName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getUnsyncedRewards(final int isAdded,
      final Continuation<? super List<Reward>> continuation) {
    final String _sql = "SELECT * FROM reward WHERE isAdded = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, isAdded);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Reward>>() {
      @Override
      public List<Reward> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfImageBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "imageBytes");
          final int _cursorIndexOfRedeemLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemLimit");
          final int _cursorIndexOfRedeemedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedCount");
          final int _cursorIndexOfRewardDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardDescription");
          final int _cursorIndexOfRewardPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardPoints");
          final int _cursorIndexOfIsAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdded");
          final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "isUpdated");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<Reward> _result = new ArrayList<Reward>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Reward _item;
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final byte[] _tmpImageBytes;
            if (_cursor.isNull(_cursorIndexOfImageBytes)) {
              _tmpImageBytes = null;
            } else {
              _tmpImageBytes = _cursor.getBlob(_cursorIndexOfImageBytes);
            }
            final int _tmpRedeemLimit;
            _tmpRedeemLimit = _cursor.getInt(_cursorIndexOfRedeemLimit);
            final int _tmpRedeemedCount;
            _tmpRedeemedCount = _cursor.getInt(_cursorIndexOfRedeemedCount);
            final String _tmpRewardDescription;
            if (_cursor.isNull(_cursorIndexOfRewardDescription)) {
              _tmpRewardDescription = null;
            } else {
              _tmpRewardDescription = _cursor.getString(_cursorIndexOfRewardDescription);
            }
            final int _tmpRewardPoints;
            _tmpRewardPoints = _cursor.getInt(_cursorIndexOfRewardPoints);
            final int _tmpIsAdded;
            _tmpIsAdded = _cursor.getInt(_cursorIndexOfIsAdded);
            final int _tmpIsUpdated;
            _tmpIsUpdated = _cursor.getInt(_cursorIndexOfIsUpdated);
            final int _tmpIsDeleted;
            _tmpIsDeleted = _cursor.getInt(_cursorIndexOfIsDeleted);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _item = new Reward(_tmpRewardName,_tmpImageBytes,_tmpRedeemLimit,_tmpRedeemedCount,_tmpRewardDescription,_tmpRewardPoints,_tmpIsAdded,_tmpIsUpdated,_tmpIsDeleted,_tmpImageUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getModifiedRewards(final int isUpdated,
      final Continuation<? super List<Reward>> continuation) {
    final String _sql = "SELECT * FROM reward WHERE isUpdated = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, isUpdated);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Reward>>() {
      @Override
      public List<Reward> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfImageBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "imageBytes");
          final int _cursorIndexOfRedeemLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemLimit");
          final int _cursorIndexOfRedeemedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedCount");
          final int _cursorIndexOfRewardDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardDescription");
          final int _cursorIndexOfRewardPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardPoints");
          final int _cursorIndexOfIsAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdded");
          final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "isUpdated");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<Reward> _result = new ArrayList<Reward>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Reward _item;
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final byte[] _tmpImageBytes;
            if (_cursor.isNull(_cursorIndexOfImageBytes)) {
              _tmpImageBytes = null;
            } else {
              _tmpImageBytes = _cursor.getBlob(_cursorIndexOfImageBytes);
            }
            final int _tmpRedeemLimit;
            _tmpRedeemLimit = _cursor.getInt(_cursorIndexOfRedeemLimit);
            final int _tmpRedeemedCount;
            _tmpRedeemedCount = _cursor.getInt(_cursorIndexOfRedeemedCount);
            final String _tmpRewardDescription;
            if (_cursor.isNull(_cursorIndexOfRewardDescription)) {
              _tmpRewardDescription = null;
            } else {
              _tmpRewardDescription = _cursor.getString(_cursorIndexOfRewardDescription);
            }
            final int _tmpRewardPoints;
            _tmpRewardPoints = _cursor.getInt(_cursorIndexOfRewardPoints);
            final int _tmpIsAdded;
            _tmpIsAdded = _cursor.getInt(_cursorIndexOfIsAdded);
            final int _tmpIsUpdated;
            _tmpIsUpdated = _cursor.getInt(_cursorIndexOfIsUpdated);
            final int _tmpIsDeleted;
            _tmpIsDeleted = _cursor.getInt(_cursorIndexOfIsDeleted);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _item = new Reward(_tmpRewardName,_tmpImageBytes,_tmpRedeemLimit,_tmpRedeemedCount,_tmpRewardDescription,_tmpRewardPoints,_tmpIsAdded,_tmpIsUpdated,_tmpIsDeleted,_tmpImageUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
