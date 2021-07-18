package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.HashMap;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        if (clips.length == 0)
            return -1;

        VideoClip lastClip = null;
        int lastClipIndex = -1;
        int minClipCount = 0;

        // 종료 시간 순으로 정렬
        quickSortByStartTime(clips);
//        System.out.println();
//        for (int i = 0; i < clips.length; ++i) {
//            System.out.println(clips[i].getStartTime() + " , " + clips[i].getEndTime());
//        }

        // 시작 클립 찾기
        for (int i = 0; i < clips.length; ++i) {
            if (clips[i].getStartTime() == 0) {
                lastClip = clips[i];
                lastClipIndex = i;
            } else
                break;

        }
        if (lastClip == null)
            return -1;
        else
            minClipCount++;

        // 다음 최적 클립 찾기
        VideoClip nextLongestClip = null;
        for (int i = lastClipIndex + 1; i < clips.length; ++i) {
            if (clips[i].getStartTime() <= lastClip.getEndTime()) {
                if (nextLongestClip == null) {
                    nextLongestClip = clips[i];
                } else {
                    if (nextLongestClip.getEndTime() < clips[i].getEndTime()) {
                        nextLongestClip = clips[i];
                    }
                }
            } else {
                if (nextLongestClip != null) {
                    lastClip = nextLongestClip;
                    nextLongestClip = null;
                    minClipCount++;
                    i--;
                } else {
                    return -1;
                }
            }

            if (lastClip.getEndTime() >= time)
                return minClipCount;
        }

        if (nextLongestClip != null) {
            lastClip = nextLongestClip;
            minClipCount++;
        }

        if (lastClip.getEndTime() < time)
            return -1;
        return minClipCount;
    }

    public static void quickSortByStartTime(final VideoClip[] videoClips) {
        if (videoClips == null)
            return;
        if (videoClips.length == 0)
            return;

        quickSortRecursiveByStartTime(videoClips, 0, videoClips.length - 1);
    }

    public static void quickSortRecursiveByStartTime(final VideoClip[] videoClips, int left, int right) {
        if (left >= right)
            return;

        int pivotPos = partitionByStartTime(videoClips, left, right);

        quickSortRecursiveByStartTime(videoClips, left, pivotPos - 1);
        quickSortRecursiveByStartTime(videoClips, pivotPos + 1, right);
    }

    public static int partitionByStartTime(final VideoClip[] videoClips, int left, int right) {
        int pivot = videoClips[right].getStartTime();
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            if (videoClips[j].getStartTime() < pivot) {
                i++;
                swap(videoClips, i, j);
            } else if (videoClips[j].getStartTime() == videoClips[right].getStartTime()) {
                if (videoClips[j].getEndTime() < videoClips[right].getEndTime()) {
                    i++;
                    swap(videoClips, i, j);
                }
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