package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.HashMap;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        if (clips.length == 0)
            return -1;

        int beforeClipStartTime = 0;
        int beforeClipEndTime = 0;
        HashMap<Integer, VideoClip> clipHashMap = new HashMap<Integer, VideoClip>();

        // 종료 시간 순으로 정렬
        quickSortByEndTime(clips);
//        System.out.println();
//        for (int i = 0; i < clips.length; ++i) {
//            System.out.println(clips[i].getStartTime() + " , " + clips[i].getEndTime());
//        }

        // 시작 클립 찾기
        int longestZeroClipIndex = -1;
        for (int i = 0; i < clips.length; ++i) {
            if (clips[i].getStartTime() == 0) {
                if (longestZeroClipIndex == -1)
                    longestZeroClipIndex = i;
                else if (clips[i].getEndTime() > clips[longestZeroClipIndex].getEndTime()) {
                    longestZeroClipIndex = i;
                }
            }
        }
        if (longestZeroClipIndex == -1)
            return -1;
        else {
            clipHashMap.put(longestZeroClipIndex, clips[longestZeroClipIndex]);
            beforeClipStartTime = clips[longestZeroClipIndex].getStartTime();
            beforeClipEndTime = clips[longestZeroClipIndex].getEndTime();
        }

        // 다음 최적 클립 찾기
        int currentIndex = -1;
        while (beforeClipEndTime < time) {
            for (int i = 0; i < clips.length; ++i) {
                if (clips[i].getStartTime() > beforeClipEndTime) continue;
                if (clips[i].getStartTime() <= beforeClipStartTime) continue;
                if (clipHashMap.containsKey(i)) continue;
                currentIndex = i;
                break;
            }

            if (currentIndex == -1) {
                return -1;
            }

            clipHashMap.put(currentIndex, clips[currentIndex]);
            beforeClipStartTime = clips[currentIndex].getStartTime();
            beforeClipEndTime = clips[currentIndex].getEndTime();
            currentIndex = -1;
        }

//        System.out.println("clip size : " + clipHashMap.size());
        return clipHashMap.size();
    }

    public static void quickSortByEndTime(final VideoClip[] videoClips) {
        if (videoClips == null)
            return;
        if (videoClips.length == 0)
            return;

        quickSortRecursiveByEndTime(videoClips, 0, videoClips.length - 1);
    }

    public static void quickSortRecursiveByEndTime(final VideoClip[] videoClips, int left, int right) {
        if (left >= right)
            return;

        int pivotPos = partitionByEndTime(videoClips, left, right);

        quickSortRecursiveByEndTime(videoClips, left, pivotPos - 1);
        quickSortRecursiveByEndTime(videoClips, pivotPos + 1, right);
    }

    public static int partitionByEndTime(final VideoClip[] videoClips, int left, int right) {
        int pivot = videoClips[right].getEndTime();
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            if (videoClips[j].getEndTime() > pivot) {
                i++;
                swap(videoClips, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(videoClips, pivotPos, right);
        return pivotPos;
    }

    public static void swap(final VideoClip[] videoClips, int i, int j) {
        var temp = videoClips[i];
        videoClips[i] = videoClips[j];
        videoClips[j] = temp;
    }
}