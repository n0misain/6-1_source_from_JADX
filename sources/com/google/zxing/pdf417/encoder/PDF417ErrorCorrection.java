package com.google.zxing.pdf417.encoder;

import android.support.v4.media.TransportMediator;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;
import com.twitter.sdk.android.core.internal.TwitterApiConstants.Errors;
import okhttp3.internal.http.StatusLine;

final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = new int[][]{new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{TwitterApiErrorConstants.MISSING_LOGIN_VERIFICATION_REQUEST, StatusLine.HTTP_PERM_REDIRECT, 436, TwitterApiErrorConstants.REGISTRATION_GENERAL_ERROR, 646, 653, 428, 379}, new int[]{274, 562, 232, 755, 599, 524, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65}, new int[]{361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, TwitterApiErrorConstants.REGISTRATION_GENERAL_ERROR, 193, 517, 273, 494, CallbackHandler.MSG_ROUTE_UNSELECTED, 147, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, 410}, new int[]{539, 422, 6, 93, 862, 771, 453, 106, 610, 287, 107, 505, 733, 877, 381, 612, 723, 476, 462, 172, 430, 609, 858, 822, 543, 376, 511, 400, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, 225, 535, 517, 352, 605, 158, 651, 201, 488, 502, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, 415, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, 148, 447, 631, 292, 908, 490, 704, 516, CallbackHandler.MSG_ROUTE_REMOVED, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, 606, 860, 569, 193, 219, 129, 186, TwitterApiErrorConstants.LOGIN_INCORRECT_CHALLENGE_RESPONSE, 287, 192, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, 156, 732, 95, 270, 447, 90, 507, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, CallbackHandler.MSG_ROUTE_SELECTED, 380, 602, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, 242, 726, 600, TwitterApiErrorConstants.USER_IS_NOT_SDK_USER, 375, 898, 845, 454, 354, TransportMediator.KEYCODE_MEDIA_RECORD, 814, 587, 804, 34, 211, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, AppLovinErrorCodes.NO_FILL, 82, 586, 708, 250, 905, 786, 138, 720, 858, 194, 311, 913, 275, FacebookRequestErrorClassification.EC_INVALID_TOKEN, 375, 850, 438, 733, 194, 280, 201, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, AppLovinErrorCodes.NO_FILL, 796, 605, 540, 913, 801, AppLovinSdk.VERSION_CODE, 799, 137, 439, 418, 592, 668, 353, 859, 370, 694, 325, TwitterApiErrorConstants.SPAMMER, 216, 257, TwitterApiErrorConstants.REGISTRATION_GENERAL_ERROR, 549, 209, 884, 315, 70, 329, 793, 490, 274, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, StatusLine.HTTP_TEMP_REDIRECT, 291, 803, 712, 19, 358, 399, 908, 103, 511, 51, 8, 517, 225, 289, 470, 637, 731, 66, 255, 917, TwitterApiErrorConstants.USER_IS_NOT_SDK_USER, 463, 830, 730, 433, 848, 585, 136, 538, 906, 90, 2, 290, 743, 199, 655, 903, 329, 49, 802, 580, 355, 588, 188, 462, 10, 134, 628, 320, 479, TransportMediator.KEYCODE_MEDIA_RECORD, 739, 71, CallbackHandler.MSG_ROUTE_UNSELECTED, 318, 374, 601, 192, 605, 142, 673, 687, 234, 722, 384, 177, 752, 607, 640, 455, 193, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, Errors.ALREADY_UNFAVORITED, 500, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, TransportMediator.KEYCODE_MEDIA_PLAY, 32, 681, 331, 792, 620, 60, 609, 441, 180, 791, 893, 754, 605, 383, 228, 749, 760, 213, 54, 297, 134, 54, 834, TwitterApiErrorConstants.DEVICE_REGISTRATION_RATE_EXCEEDED, 922, 191, 910, 532, 609, 829, 189, 20, 167, 29, 872, 449, 83, 402, 41, 656, 505, 579, 481, 173, 404, 251, 688, 95, 497, 555, 642, 543, StatusLine.HTTP_TEMP_REDIRECT, 159, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, 409, 574, 118, 498, TwitterApiErrorConstants.DEVICE_ALREADY_REGISTERED, 380, 350, 492, 197, 265, 920, 155, 914, TwitterApiErrorConstants.DEVICE_REGISTRATION_RATE_EXCEEDED, 229, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, 327, 520, 435, 543, 203, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, AppLovinErrorCodes.NO_FILL, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, 142, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, 513, 192, 516, CallbackHandler.MSG_ROUTE_REMOVED, TwitterApiErrorConstants.SPAMMER, 518, 794, 395, 768, 848, 51, 610, 384, 168, FacebookRequestErrorClassification.EC_INVALID_TOKEN, 826, 328, 596, 786, TwitterApiErrorConstants.REGISTRATION_PHONE_NORMALIZATION_FAILED, 570, 381, 415, 641, 156, TwitterApiErrorConstants.MISSING_LOGIN_VERIFICATION_REQUEST, 151, 429, 531, 207, 676, 710, 89, 168, 304, 402, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, 902, 452, 167, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, 420, TwitterApiErrorConstants.OVER_LIMIT_LOGIN_VERIFICATION_START, 288, 594, 394, 511, 327, 589, 777, 699, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, 564, 343, 693, 109, 608, 563, 365, 181, 772, 677, 310, 248, 353, 708, 410, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, 424, 833, 77, 597, 346, TwitterApiErrorConstants.USER_IS_NOT_SDK_USER, 757, 632, 695, 751, 331, 247, 184, 45, 787, 680, 18, 66, 407, 369, 54, 492, 228, 613, 830, 922, 437, 519, 644, 905, 789, 420, 305, 441, 207, TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT, 892, 827, 141, 537, 381, 662, 513, 56, 252, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 242, 797, 838, 837, 720, 224, StatusLine.HTTP_TEMP_REDIRECT, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, 249, 321, 881, 699, 535, 673, 782, 210, 815, 905, TwitterApiErrorConstants.REGISTRATION_PHONE_NORMALIZATION_FAILED, 843, 922, 281, 73, 469, 791, 660, 162, 498, StatusLine.HTTP_PERM_REDIRECT, 155, 422, 907, 817, 187, 62, 16, 425, 535, 336, TwitterApiErrorConstants.OPERATOR_UNSUPPORTED, 437, 375, 273, 610, 296, 183, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, 424, 242, 749, 321, 54, 669, 316, 342, TwitterApiErrorConstants.DEVICE_REGISTRATION_RATE_EXCEEDED, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, 610, 46, 656, 447, 171, 616, 464, FacebookRequestErrorClassification.EC_INVALID_TOKEN, 531, 297, 321, 762, 752, 533, 175, 134, 14, 381, 433, 717, 45, 111, 20, 596, TwitterApiErrorConstants.REGISTRATION_GENERAL_ERROR, 736, 138, 646, 411, 877, 669, 141, 919, 45, 780, 407, 164, 332, 899, 165, 726, 600, 325, 498, 655, 357, 752, 768, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, 675, 410, 389, 244, 31, 121, TwitterApiErrorConstants.REGISTRATION_PHONE_NORMALIZATION_FAILED, CallbackHandler.MSG_ROUTE_UNSELECTED}};

    private PDF417ErrorCorrection() {
    }

    static int getErrorCorrectionCodewordCount(int errorCorrectionLevel) {
        if (errorCorrectionLevel >= 0 && errorCorrectionLevel <= 8) {
            return 1 << (errorCorrectionLevel + 1);
        }
        throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int n) throws WriterException {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        } else if (n <= 40) {
            return 2;
        } else {
            if (n <= 160) {
                return 3;
            }
            if (n <= 320) {
                return 4;
            }
            if (n <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
    }

    static String generateErrorCorrection(CharSequence dataCodewords, int errorCorrectionLevel) {
        int j;
        int k = getErrorCorrectionCodewordCount(errorCorrectionLevel);
        char[] e = new char[k];
        int sld = dataCodewords.length();
        for (int i = 0; i < sld; i++) {
            int t1 = (dataCodewords.charAt(i) + e[k - 1]) % PDF417Common.NUMBER_OF_CODEWORDS;
            for (j = k - 1; j > 0; j--) {
                e[j] = (char) ((e[j - 1] + (929 - ((EC_COEFFICIENTS[errorCorrectionLevel][j] * t1) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
            }
            e[0] = (char) ((929 - ((EC_COEFFICIENTS[errorCorrectionLevel][0] * t1) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(k);
        for (j = k - 1; j >= 0; j--) {
            if (e[j] != '\u0000') {
                e[j] = (char) (929 - e[j]);
            }
            sb.append(e[j]);
        }
        return sb.toString();
    }
}
