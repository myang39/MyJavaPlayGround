package util;

class SegmentTree {
  int leftBound;
  int rightBound;
  SegmentTree leftChild;
  SegmentTree rightChild;
  int mid = 0;
  int val = 0;
  int sum = 0;

  SegmentTree(int[] nums, int leftBound, int rightBound) {
    this.leftBound = leftBound;
    this.rightBound = rightBound;
    if (leftBound != rightBound) {
      this.mid = leftBound + (rightBound - leftBound) / 2;
      this.leftChild = new SegmentTree(nums, leftBound, mid);
      this.rightChild = new SegmentTree(nums, mid + 1, rightBound);
    }
  }

  public int sum(int left, int right) {
    if (left <= leftBound && rightBound <= right) {
      return sum;
    } else if (leftBound > right || rightBound < left) {
      return 0;
    } else {
      return leftChild.sum(left, right) + rightChild.sum(left, right);
    }
  }

  public void update(int index, int val) {
    if (leftBound == index && rightBound == index) {
      this.val = val;
      this.sum = val;
      return;
    }
    if (index <= this.mid) {
      leftChild.update(index, val);
    } else {
      rightChild.update(index, val);
    }
  }
}