import os
import re

PATH = os.path.abspath(os.path.dirname(__file__))


def rename(n):
    for root, _, files in os.walk(PATH, topdown=True):
        for f in files:
            if re.match(r'\d+\.\w+', f):
                index = f.find('.')
                os.rename(os.path.join(root, f),
                          os.path.join(root, f[:index].zfill(n) + f[index:]))


if __name__ == "__main__":
    rename(5)
