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
public final class QuizHistoryDao_Impl implements QuizHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QuizHistory> __insertionAdapterOfQuizHistory;

  public QuizHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuizHistory = new EntityInsertionAdapter<QuizHistory>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `quiz_history` (`id`,`userId`,`materialName`,`setName`,`score`,`date`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QuizHistory value) {
        stmt.bindLong(1, value.getId());
        if (value.getUserId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserId());
        }
        if (value.getMaterialName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMaterialName());
        }
        if (value.getSetName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSetName());
        }
        if (value.getScore() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getScore());
        }
        stmt.bindLong(6, value.getDate());
      }
    };
  }

  @Override
  public Object insertQuizHistory(final QuizHistory quizHistory,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQuizHistory.insert(quizHistory);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllQuizHistory(final String userDocId,
      final Continuation<? super List<QuizHistory>> continuation) {
    final String _sql = "SELECT * FROM quiz_history WHERE userId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userDocId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userDocId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<QuizHistory>>() {
      @Override
      public List<QuizHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfMaterialName = CursorUtil.getColumnIndexOrThrow(_cursor, "materialName");
          final int _cursorIndexOfSetName = CursorUtil.getColumnIndexOrThrow(_cursor, "setName");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<QuizHistory> _result = new ArrayList<QuizHistory>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final QuizHistory _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpMaterialName;
            if (_cursor.isNull(_cursorIndexOfMaterialName)) {
              _tmpMaterialName = null;
            } else {
              _tmpMaterialName = _cursor.getString(_cursorIndexOfMaterialName);
            }
            final String _tmpSetName;
            if (_cursor.isNull(_cursorIndexOfSetName)) {
              _tmpSetName = null;
            } else {
              _tmpSetName = _cursor.getString(_cursorIndexOfSetName);
            }
            final String _tmpScore;
            if (_cursor.isNull(_cursorIndexOfScore)) {
              _tmpScore = null;
            } else {
              _tmpScore = _cursor.getString(_cursorIndexOfScore);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new QuizHistory(_tmpId,_tmpUserId,_tmpMaterialName,_tmpSetName,_tmpScore,_tmpDate);
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
