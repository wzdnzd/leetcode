import os
import re

PATH = os.path.abspath(os.path.dirname(__file__))


def rename(n):
    for root, _, files in os.walk(PATH, topdown=True):
        for f in files:
            if re.match(r'\d+\.\w+', f):
                index = f.find('.')

                if index == n:
                    continue

                filename = os.path.join(root, f[:index].zfill(n) + f[index:])
                if os.path.exists(filename):
                    os.remove(filename)

                os.rename(os.path.join(root, f), filename)


if __name__ == "__main__":
    rename(5)
