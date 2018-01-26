package task_557;

class MultiThread extends Thread {
    private final int[][] firstMatrix;
    private final int[][] secondMatrix;

    private final String[] input;
    private final int[][] resultMatrix;
    private final int firstIndex;
    private final int lastIndex;
    private final int sizeMatrix;
    private final int countMatrix;
    private final int p;

    public MultiThread(String[] input, int[][] resultMatrix, int firstIndex, int lastIndex, int sizeMatrix, int countMatrix, int p) {
        this.input = input;
        this.resultMatrix = resultMatrix;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.sizeMatrix = sizeMatrix;
        this.countMatrix = countMatrix;
        this.p = p;

        firstMatrix = new int[sizeMatrix][sizeMatrix];
        secondMatrix = new int[sizeMatrix][sizeMatrix];
    }

    private void createMatrix(int i) {
        String[] s;
        if (i != 1) {
            for (int j = 0; j < sizeMatrix; j++) {
                System.arraycopy(resultMatrix[j], 0, firstMatrix[j], 0, sizeMatrix);
            }
        } else {
            for (int j = 0; j < sizeMatrix; j++) {
                s = input[j].split(" ");
                for (int k = 0; k < sizeMatrix; k++) {
                    firstMatrix[j][k] = Integer.parseInt(s[k]);
                }
            }
        }
        for (int j = 0; j < sizeMatrix; j++) {
            s = input[j + i * sizeMatrix].split(" ");
            for (int k = 0; k < sizeMatrix; k++) {
                secondMatrix[j][k] = Integer.parseInt(s[k]);
            }
        }
    }

    private void calcValue(final int row, final int col) {
        int sum = 0;
        for (int i = 0; i < sizeMatrix; ++i) {
            sum += firstMatrix[row][i] * secondMatrix[i][col];
        }
        if (sum >= Math.abs(p)) sum = sum % p;
        resultMatrix[row][col] = sum;
    }

    public void run() {
        for (int i = 1; i < countMatrix; i++) {
            createMatrix(i);
            for (int index = firstIndex; index < lastIndex; index++) {
                calcValue(index / sizeMatrix, index % sizeMatrix);
            }
        }
    }
}
