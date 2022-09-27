class ComplexNumber {

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ComplexNumber cn)) return false;

        return cn.getRe() == this.getRe()
                && cn.getIm() == this.getIm();
    }

    @Override
    public int hashCode() {
        int result = 17;

        long re = Double.doubleToLongBits(getRe());
        long im = Double.doubleToLongBits(getIm());

        int iRe = (int)(re ^ (re >>> 32));
        int iIm = (int)(im ^ (im >>> 32));

        result = 31 * result + iRe;
        result = 31 * result + iIm;
        return result;
    }
}