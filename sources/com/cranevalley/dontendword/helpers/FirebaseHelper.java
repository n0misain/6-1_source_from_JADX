package com.cranevalley.dontendword.helpers;

import android.support.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class FirebaseHelper {
    public static String getUserId(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            return firebaseUser.getUid();
        }
        return null;
    }

    public static String getDisplayName(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            for (UserInfo userInfo : firebaseUser.getProviderData()) {
                String displayName = userInfo.getDisplayName();
                if (displayName != null && !displayName.isEmpty()) {
                    return displayName;
                }
            }
        }
        return null;
    }

    public static String getPhotoUrl(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            for (UserInfo userInfo : firebaseUser.getProviderData()) {
                String photoUrl;
                if (userInfo.getPhotoUrl() != null) {
                    photoUrl = userInfo.getPhotoUrl().toString();
                } else {
                    photoUrl = null;
                }
                if (photoUrl != null && !photoUrl.isEmpty()) {
                    return photoUrl;
                }
            }
        }
        return null;
    }

    public static DatabaseReference getServerTimeOffsetReference() {
        return FirebaseDatabase.getInstance().getReference(".info/serverTimeOffset");
    }

    public static DatabaseReference getAllUserInfosReference() {
        return FirebaseDatabase.getInstance().getReference("userInfos");
    }

    public static DatabaseReference getPlayerUserInfoReference() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return FirebaseDatabase.getInstance().getReference("userInfos/" + firebaseUser.getUid());
        }
        return FirebaseDatabase.getInstance().getReference("falseNode");
    }

    public static DatabaseReference getPlayerFriendsReference() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return FirebaseDatabase.getInstance().getReference("friends/" + firebaseUser.getUid());
        }
        return FirebaseDatabase.getInstance().getReference("falseNode");
    }

    public static DatabaseReference getPlayerChatWithOpponentReference(String opponentUserId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return FirebaseDatabase.getInstance().getReference("chats/" + firebaseUser.getUid() + "/" + opponentUserId);
        }
        return FirebaseDatabase.getInstance().getReference("falseNode");
    }

    public static DatabaseReference getPlayerNewMessagesReference() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return FirebaseDatabase.getInstance().getReference("newMessages/" + firebaseUser.getUid());
        }
        return FirebaseDatabase.getInstance().getReference("falseNode");
    }

    public static DatabaseReference getPlayerGamesReference() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return FirebaseDatabase.getInstance().getReference("games/" + firebaseUser.getUid());
        }
        return FirebaseDatabase.getInstance().getReference("falseNode");
    }

    public static DatabaseReference getRandomGamesReference() {
        return FirebaseDatabase.getInstance().getReference("randomGames");
    }

    public static DatabaseReference getPlayerGameReference(String gameId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return FirebaseDatabase.getInstance().getReference("games/" + firebaseUser.getUid() + "/" + gameId);
        }
        return FirebaseDatabase.getInstance().getReference("falseNode");
    }

    public static void addToken(String token) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference playerTokensReference = FirebaseDatabase.getInstance().getReference("tokens/" + firebaseUser.getUid());
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put(token, Boolean.valueOf(true));
            playerTokensReference.updateChildren(updatesMap);
        }
    }

    public static void setDisplayName(String displayName) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference playerUserInfoReference = FirebaseDatabase.getInstance().getReference("userInfos/" + firebaseUser.getUid());
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put("displayName", displayName);
            playerUserInfoReference.updateChildren(updatesMap);
        }
    }

    public static void setPhotoUrl(String photoUrl) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference playerUserInfoReference = FirebaseDatabase.getInstance().getReference("userInfos/" + firebaseUser.getUid());
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put("photoUrl", photoUrl);
            playerUserInfoReference.updateChildren(updatesMap);
        }
    }

    public static void addFriend(String opponentUserId, String opponentDisplayName, @Nullable String opponentPhotoUrl) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference playerFriendsReference = FirebaseDatabase.getInstance().getReference("friends/" + firebaseUser.getUid());
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put(opponentUserId + "/displayName", opponentDisplayName);
            updatesMap.put(opponentUserId + "/photoUrl", opponentPhotoUrl);
            playerFriendsReference.updateChildren(updatesMap);
        }
    }

    public static void removeFriend(String opponentUserId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference playerFriendsReference = FirebaseDatabase.getInstance().getReference("friends/" + firebaseUser.getUid());
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put(opponentUserId, null);
            playerFriendsReference.updateChildren(updatesMap);
        }
    }

    public static void sendMessage(String opponentUserId, String text) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference messageReference = FirebaseDatabase.getInstance().getReference("chats/" + firebaseUser.getUid() + "/" + opponentUserId).push();
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put("text", text);
            messageReference.updateChildren(updatesMap);
        }
    }

    public static void clearNewMessage(String opponentUserId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference playerNewMessagesReference = FirebaseDatabase.getInstance().getReference("newMessages/" + firebaseUser.getUid());
            Map<String, Object> updatesMap = new HashMap();
            updatesMap.put(opponentUserId, null);
            playerNewMessagesReference.updateChildren(updatesMap);
        }
    }

    public static Task<Void> createRandomGame(String gameId, String letters) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/createRandomGame").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        updatesMap.put("letters", letters.toUpperCase(Locale.ENGLISH));
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> joinRandomGame(String gameId, String opponentUserId, String opponentDisplayName, String opponentPhotoUrl, String letters) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/joinRandomGame").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        updatesMap.put("opponentUserId", opponentUserId);
        updatesMap.put("opponentDisplayName", opponentDisplayName);
        updatesMap.put("opponentPhotoUrl", opponentPhotoUrl);
        updatesMap.put("letters", letters.toUpperCase(Locale.ENGLISH));
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> createFriendGame(String gameId, String opponentUserId, String opponentDisplayName, String opponentPhotoUrl, String letters) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/createFriendGame").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        updatesMap.put("opponentUserId", opponentUserId);
        updatesMap.put("opponentDisplayName", opponentDisplayName);
        updatesMap.put("opponentPhotoUrl", opponentPhotoUrl);
        updatesMap.put("letters", letters.toUpperCase(Locale.ENGLISH));
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> addLetter(String gameId, String addedLetter, boolean front) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/addLetter").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        updatesMap.put("addedLetter", addedLetter.toUpperCase(Locale.ENGLISH));
        updatesMap.put("front", Boolean.valueOf(front));
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> sendChallenge(String gameId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/sendChallenge").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> submitDefence(String gameId, String word, @Nullable String definition) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/submitDefence").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        updatesMap.put("word", word.toUpperCase(Locale.ENGLISH));
        updatesMap.put("definition", definition);
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> sendNudge(String gameId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/sendNudge").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> claimWinWithDefinition(String gameId, String definition) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/claimWinWithDefinition").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        updatesMap.put("definition", definition);
        return commandReference.updateChildren(updatesMap);
    }

    public static Task<Void> claimWinForTimeOut(String gameId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Tasks.forResult(null);
        }
        DatabaseReference commandReference = FirebaseDatabase.getInstance().getReference("commands/" + firebaseUser.getUid() + "/claimWinForTimeOut").push();
        Map<String, Object> updatesMap = new HashMap();
        updatesMap.put("gameId", gameId);
        return commandReference.updateChildren(updatesMap);
    }

    private FirebaseHelper() {
    }
}
