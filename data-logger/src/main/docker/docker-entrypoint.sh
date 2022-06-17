#!/bin/bash

#
# Copyright (C) 2022 Link Intersystems GmbH
#
# This program and the accompanying materials
#  are made available under the terms of the Eclipse Public License 2.0
#  which accompanies this distribution, and is available at
#  https://www.eclipse.org/legal/epl-2.0/
#
#   SPDX-License-Identifier: EPL-2.0
#
#   Contributors:
#       Link Intersystems GmbH - initial API and implementation
#
which $1 >/dev/null 2>&1
if [ $? -eq 0 ]; then
  exec "$@"
else
  exec java -jar /app.jar "$@"
fi
