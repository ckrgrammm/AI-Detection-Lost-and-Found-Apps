package com.example.fyps.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class RewardHistoryDao_Impl implements RewardHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RewardHistory> __insertionAdapterOfRewardHistory;

  public RewardHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRewardHistory = new EntityInsertionAdapter<RewardHistory>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `reward_history` (`id`,`userDocId`,`redeemedDate`,`rewardName`,`rewardDetails`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RewardHistory value) {
        stmt.bindLong(1, value.getId());
        if (value.getUserDocId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserDocId());
        }
        stmt.bindLong(3, value.getRedeemedDate());
        if (value.getRewardName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRewardName());
        }
        if (value.getRewardDetails() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRewardDetails());
        }
      }
    };
  }

  @Override
  public Object insertRewardHistory(final RewardHistory rewardHistory,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRewardHistory.insert(rewardHistory);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllRewardHistory(final String userDocId,
      final Continuation<? super List<RewardHistory>> continuation) {
    final String _sql = "SELECT * FROM reward_history WHERE userDocId = ? ORDER BY redeemedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userDocId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userDocId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RewardHistory>>() {
      @Override
      public List<RewardHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserDocId = CursorUtil.getColumnIndexOrThrow(_cursor, "userDocId");
          final int _cursorIndexOfRedeemedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedDate");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfRewardDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardDetails");
          final List<RewardHistory> _result = new ArrayList<RewardHistory>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final RewardHistory _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUserDocId;
            if (_cursor.isNull(_cursorIndexOfUserDocId)) {
              _tmpUserDocId = null;
            } else {
              _tmpUserDocId = _cursor.getString(_cursorIndexOfUserDocId);
            }
            final long _tmpRedeemedDate;
            _tmpRedeemedDate = _cursor.getLong(_cursorIndexOfRedeemedDate);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final String _tmpRewardDetails;
            if (_cursor.isNull(_cursorIndexOfRewardDetails)) {
              _tmpRewardDetails = null;
            } else {
              _tmpRewardDetails = _cursor.getString(_cursorIndexOfRewardDetails);
            }
            _item = new RewardHistory(_tmpId,_tmpUserDocId,_tmpRedeemedDate,_tmpRewardName,_tmpRewardDetails);
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
