package com.company.myPOJO;

import java.util.Objects;

public class Hashes {
    public String sha1;
    public String sha256;

    public Hashes() {
    }

    public Hashes(String sha1, String sha256) {
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashes hashes = (Hashes) o;
        return Objects.equals(sha1, hashes.sha1) && Objects.equals(sha256, hashes.sha256);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha1, sha256);
    }

    @Override
    public String toString() {
        return "Hashes{" +
                "sha1='" + sha1 + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }
}
